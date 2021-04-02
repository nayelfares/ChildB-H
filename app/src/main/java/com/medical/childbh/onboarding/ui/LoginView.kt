package com.medical.childbh.onboarding.ui

import com.medical.childbh.onboarding.model.LoginData

interface LoginView {
    fun loginSuccess(data: LoginData)
    fun loginFailed(message: String)
}