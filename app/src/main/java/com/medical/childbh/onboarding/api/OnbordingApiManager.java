package com.medical.childbh.onboarding.api;


import com.medical.childbh.ApiManager;

public class OnbordingApiManager {
    public static OnboardingService onboardingService = ApiManager.retrofit.create(OnboardingService.class);
}


