package com.medical.childbh.parent.api

import com.medical.childbh.ApiManager

object ParentApiManager {
    var parentService = ApiManager.retrofit.create<ParentService>(ParentService::class.java)
}