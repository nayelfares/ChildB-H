package com.medical.childbh.parent.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
data class Article(
        val id :Int,
    val photo:String,
    val category:String,
    val title:String,
    val body:String
    ): Parcelable

data class ArticleResult(val success:Boolean,val message:String,val data: ArrayList<Article>)