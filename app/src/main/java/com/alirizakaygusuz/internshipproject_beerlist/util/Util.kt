package com.alirizakaygusuz.internshipproject_beerlist.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.alirizakaygusuz.internshipproject_beerlist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


//extension function
fun ImageView.downloadFromUrl(url: String , progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide
        .with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth = 9.5f
        centerRadius = 50f

        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView , url: String?){
    url?.let { view.downloadFromUrl(it, placeholderProgressBar(view.context)) }
}