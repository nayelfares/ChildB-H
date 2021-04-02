package com.medical.childbh.onboarding.api

import com.medical.childbh.onboarding.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface OnboardingService {
    @POST("user-login")
    fun login(
            @Query("email") email: String,
            @Query("password") password: String
    ): Observable<LoginResponse>

    @POST("user-register")
    fun register(
            @Query("first_name") first_name: String,
            @Query("last_name") last_name: String,
            @Query("user_name") user_name: String,
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("phone") phone: String,
            @Query("type") type: String,
            @Query("speciality") speciality: String,
            @Query("collage") collage: String,
            @Query("previous_clincs") previous_clincs: String,
            @Query("clinic_address") clinic_address: String,
            @Query("experience_years") experience_years: String,
            @Query("certificate_number") certificate_number: String,
            @Query("child_name") child_name: String,
            @Query("child_age") child_age: String,
            @Query("parent_job") parent_job: String,
            @Query("marriage_status") marriage_status: String,
            @Query("parent_gender") parent_gender: String,
            @Query("child_number") child_number: String,
            @Query("child_main_problem") child_main_problem: String
    ): Observable<LoginResponse>
}