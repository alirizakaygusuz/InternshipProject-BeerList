package com.alirizakaygusuz.internshipproject_beerlist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirizakaygusuz.internshipproject_beerlist.model.Beer
import com.alirizakaygusuz.internshipproject_beerlist.service.BeerDatabase
import com.alirizakaygusuz.internshipproject_beerlist.service.PunkAPIService
import com.alirizakaygusuz.internshipproject_beerlist.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BeerViewModel(application: Application) : BaseViewModel(application) {

    private val punkAPIService = PunkAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 24 * 60 * 60 * 1000L

    val beer = MutableLiveData<List<Beer>>()


    fun refreshData() {

        val lastSave_time = customSharedPreferences.getTime()

        if (lastSave_time != null && lastSave_time.toInt() != 0 && System.currentTimeMillis() - lastSave_time < refreshTime) {
            getDataFromDatabase()

        } else {
            getDataFromAPI()
        }
    }

    fun refreshFromAPI() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {

        disposable.add(
            punkAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Beer>>() {
                    override fun onSuccess(t: List<Beer>) {

                        storeInSQLite(t)

                    }

                    override fun onError(e: Throwable) {

                        e.printStackTrace()
                    }

                })
        )
    }

    private fun getDataFromDatabase() {
        launch {
            val beerList = BeerDatabase(getApplication()).beerDao().getAllBeer()
            beer.value = beerList
        }
    }

    private fun storeInSQLite(beerList: List<Beer>) {
        launch {
            val dao = BeerDatabase(getApplication()).beerDao()

            dao.deleteAll()

            //List-> individual
            val longIdList = dao.insertAll(*beerList.toTypedArray())

            var i = 0

            while (i < beerList.size) {
                beerList[i].id = longIdList[i].toInt()

                i = i + 1
            }

            beer.value = beerList
        }

        customSharedPreferences.saveTime(System.currentTimeMillis())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}