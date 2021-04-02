package com.medical.childbh.parent.api

import com.medical.childbh.parent.model.ArticleResult
import com.medical.childbh.parent.model.DoctorsListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ParentService {
    @GET("article")
    fun getArticleList(
            @Header("Authorization")  token:String
    ): Observable<ArticleResult>

    @GET("doctor")
    fun getDoctorsList(
            @Header("Authorization")  token:String
    ): Observable<DoctorsListResult>
}