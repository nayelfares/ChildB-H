package com.medical.childbh.onboarding.api

import com.medical.childbh.ApiManager

object OnbordingApiManager {
    var onboardingService = ApiManager.retrofit.create<OnboardingService>(OnboardingService::class.java)
}