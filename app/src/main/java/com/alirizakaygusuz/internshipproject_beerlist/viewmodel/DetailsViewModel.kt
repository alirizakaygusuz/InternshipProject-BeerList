package com.alirizakaygusuz.internshipproject_beerlist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirizakaygusuz.internshipproject_beerlist.model.Beer
import com.alirizakaygusuz.internshipproject_beerlist.service.BeerDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application): BaseViewModel (application){

    val selectedBeer = MutableLiveData<Beer>()

    fun getDataFromDatabase(id: Int){
        launch {

            val beerDao = BeerDatabase(getApplication()).beerDao()

            val beer = beerDao.getBeer(id)

            selectedBeer.value = beer
        }
    }
}