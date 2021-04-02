package com.medical.childbh.parent.api

import com.medical.childbh.GeneralResponse
import com.medical.childbh.parent.model.ArticleResult
import com.medical.childbh.parent.model.ChildrenResult
import com.medical.childbh.parent.model.DoctorsListResult
import io.reactivex.Observable
import retrofit2.http.*

interface ParentService {
    @GET("article")
    fun getArticleList(
            @Header("Authorization")  token:String
    ): Observable<ArticleResult>

    @GET("doctor")
    fun getDoctorsList(
            @Header("Authorization")  token:String
    ): Observable<DoctorsListResult>

    @POST("doctor/rating/add")
    fun rate(
            @Header("Authorization")  token:String,
            @Query("doctor_id") doctor_id:Int,
            @Query("value") value:Int,
            @Query("parent_id") parent_id:Int
    ): Observable<GeneralResponse>

    @GET("child")
    fun getChildren(
            @Header("Authorization")  token     :String,
            @Query("parent_id")       parent_id :Int
    ): Observable<ChildrenResult>

    @DELETE("child/{id}")
    fun deleteChild(
            @Header("Authorization")  token     :String,
            @Path("id")       id :Int
    ): Observable<GeneralResponse>
}