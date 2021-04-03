package com.medical.childbh.doctor.api

import com.medical.childbh.ApiManager

object DoctorApiManager {
    var doctorService = ApiManager.retrofit.create<DoctorService>(DoctorService::class.java)
}