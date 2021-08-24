package com.alirizakaygusuz.internshipproject_beerlist.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alirizakaygusuz.internshipproject_beerlist.model.Beer

@Database(entities = arrayOf(Beer::class), version = 1)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {

        @Volatile
        private var instance: BeerDatabase? = null

        private val lock = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?:makeDatabase(context = context ).also { beerDatabase ->
                instance = beerDatabase
            }
        }


        private fun makeDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                BeerDatabase::class.java,
                "beerdatabase"
            ).build()

    }

}