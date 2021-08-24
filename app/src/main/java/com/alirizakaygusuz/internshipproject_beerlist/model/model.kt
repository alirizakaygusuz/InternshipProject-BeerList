package com.alirizakaygusuz.internshipproject_beerlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Beer(


   // @SerializedName("name")
    //@ColumnInfo(name = "name")

    @ColumnInfo
    val name: String? ,

    @ColumnInfo
    val tagline: String? ,

    @ColumnInfo
    val first_brewed: String? ,

    @ColumnInfo
    val description: String ? ,

    @ColumnInfo
    val image_url: String? ,

    @ColumnInfo
    val brewers_tips: String?

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}