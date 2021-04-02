package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Child

interface ChildrenView {
    fun getChildrenSuccess(children:ArrayList<Child>)
    fun getChildrenFailed(message:String)
}