package com.medical.childbh.parent.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList
@Parcelize
data class Doctor(
    val id:Int,
    val name:String,
    val photo:String,
    val address:String,
    val phone:String?,
    val dob:String,
    val email:String,
    val details: String?,
    val specialization:String?,
    val review: Float,
    var selected :Boolean=false
    ): Parcelable

data class DoctorsListResult(val success:Boolean,val message:String,val data: ArrayList<Doctor>)