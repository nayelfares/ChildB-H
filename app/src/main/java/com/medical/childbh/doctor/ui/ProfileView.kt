package com.medical.childbh.doctor.ui

import android.net.Uri
import com.medical.childbh.parent.model.Doctor

interface ProfileView {
    fun getProfileSuccess(profile: Doctor)
    fun getProfileFailed(message:String)

    fun updatePhotoOnSuccess(message:String,uri: Uri)
    fun updatePhotoOnSFailer(message:String)
    fun updateProfileSuccess(message: String)
    fun updateProfileFailed(message: String)
}