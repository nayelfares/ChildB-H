package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Doctor
import java.util.ArrayList

interface AddConsultantView {
    fun getDoctorsListSuccess(doctors: ArrayList<Doctor>)
    fun getDoctorsListFailed(message: String)
    fun addConsultationSuccess(message: String)
    fun addConsultationFailed(message: String)
}