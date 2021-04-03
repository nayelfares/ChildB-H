package com.medical.childbh.doctor.ui

import com.medical.childbh.doctor.model.Parent
import java.util.ArrayList

interface ParentsView {
    fun getParentsSuccess(parents: ArrayList<Parent>)
    fun getParentsFailed(message: String)
}