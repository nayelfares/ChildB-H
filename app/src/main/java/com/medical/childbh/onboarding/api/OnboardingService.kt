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
            @Query("name") name: String,
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("phone") phone: String,
            @Query("type") type: String,
            @Query("speciality") speciality: String,
            @Query("address") clinic_address: String
    ): Observable<LoginResponse>
}