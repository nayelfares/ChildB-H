package com.medical.childbh.parent.api

import com.medical.childbh.parent.model.ArticleResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ParentService {
    @GET("article")
    fun getArticleList(
            @Header("Authorization")  token:String
    ): Observable<ArticleResult>
}