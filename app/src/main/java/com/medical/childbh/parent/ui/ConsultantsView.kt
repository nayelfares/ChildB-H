package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Doctor


interface ConsultantsView {
    fun onFailer(toString: String)
    fun onSuccess(doctors: ArrayList<Doctor>)
}