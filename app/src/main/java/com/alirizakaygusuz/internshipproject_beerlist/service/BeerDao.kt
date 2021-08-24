package com.alirizakaygusuz.internshipproject_beerlist.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alirizakaygusuz.internshipproject_beerlist.model.Beer

@Dao
interface BeerDao {


    @Query("SELECT * FROM beer")
    suspend fun getAllBeer(): List<Beer>

    @Query("SELECT * FROM beer WHERE id = :id")
    suspend fun getBeer(id: Int): Beer

    @Insert
    suspend fun insertAll(vararg beer: Beer): List<Long>

    @Query("DELETE FROM beer")
    suspend fun deleteAll()



}