package com.alirizakaygusuz.internshipproject_beerlist.service

import com.alirizakaygusuz.internshipproject_beerlist.model.Beer
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PunkAPIService {

    private val BASE_URL = "https://api.punkapi.com/v2/"
    private val punkAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PunkAPI::class.java)


     fun getData(): Single<List<Beer>>{
        return  punkAPI.getBeerList()
    }
}