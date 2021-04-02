package com.medical.childbh.parent.model

import java.util.ArrayList

data class Child (
        val id  :Int,
        val name :String,
        val photo :String,
        val information:String,
        val dob:String,
        val gender:String,
        val parent_id:Int
        )

data class ChildrenResult(val success:Boolean,val message:String,val data: ArrayList<Child>)