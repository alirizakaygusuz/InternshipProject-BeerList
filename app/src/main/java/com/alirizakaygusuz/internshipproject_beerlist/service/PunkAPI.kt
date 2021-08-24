package com.alirizakaygusuz.internshipproject_beerlist.service

import com.alirizakaygusuz.internshipproject_beerlist.model.Beer
import io.reactivex.Single
import retrofit2.http.GET

interface PunkAPI {

    @GET("beers")
    fun getBeerList():Single<List<Beer>>
}