package com.medical.childbh.doctor.api

import com.medical.childbh.GeneralResponse
import com.medical.childbh.doctor.model.ParentsResponse
import com.medical.childbh.doctor.model.ProfileResponse
import com.medical.childbh.parent.model.ReportsResult
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface DoctorService {

    @GET("user-profile_get")
    fun getProfile(
            @Header("Authorization")  token:String,
            @Query("id") id:Int
    ): Observable<ProfileResponse>

    @POST("change_photo")
    @Multipart
    fun upload(
            @Header("Authorization") authorization:String,
            @Part photo: MultipartBody.Part,
            @Query("id") id:Int
    ): Observable<GeneralResponse>

    @POST("user-profile_update")
    fun updateProfile(
            @Header("Authorization")  token:String,
            @Query("id") id:Int,
            @Query("name") name:String,
            @Query("phone") phone:String,
            @Query("address") address:String,
            @Query("specialization") specialization:String
    ): Observable<GeneralResponse>

    @GET("get_parent")
    fun getParents(
            @Header("Authorization")  token:String
    ): Observable<ParentsResponse>

    @PUT("consultation/{id}")
    fun addAnswer(
            @Header("Authorization") authorization:String,
            @Path("id") id:Int,
            @Query("answer") answer:String
    ): Observable<GeneralResponse>

    @GET("need_answer")
    fun getActiveQuestions(
            @Header("Authorization") token: String,
            @Query("doctor_id") doctor_id: Int
    ): Observable<ReportsResult>
}