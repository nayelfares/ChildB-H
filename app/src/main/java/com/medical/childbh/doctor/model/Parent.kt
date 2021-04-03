package com.medical.childbh.doctor.model

import java.util.ArrayList

data class Parent (
        val id:Int,
        val name:String,
        val phone:String
        )

class ParentsResponse(val success:Boolean,val message:String,val data: ArrayList<Parent>)