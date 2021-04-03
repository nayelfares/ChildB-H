package com.medical.childbh.parent.model

import java.util.ArrayList

data class Report (
        val id:Int,
        val child:String,
        val doctor:String,
        val question :String,
        val answer :String
)

data class ReportsResult(val success:Boolean,val message:String,val data: ArrayList<Report>)