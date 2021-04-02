package com.medical.childbh.onboarding.vm

import android.content.Context
import com.medical.childbh.onboarding.api.OnbordingApiManager
import com.medical.childbh.onboarding.model.LoginResponse
import com.medical.childbh.onboarding.ui.RegistrationView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel(var registrationView: RegistrationView, var context: Context) {
    fun register(
            first_name         : String,
            last_name          : String,
            user_name          : String,
            email              : String,
            password           : String,
            phone              : String,
            type               : String,
            speciality         : String,
            collage            : String,
            previous_clincs    : String,
            clinic_address     : String,
            experience_years   : String,
            certificate_number : String,
            child_name         : String,
            child_age          : String,
            parent_job         : String,
            marriage_status    : String,
            parent_gender      : String,
            child_number       : String,
            child_main_problem : String
    ) {
        val loginObservable = OnbordingApiManager.onboardingService.register(
                first_name, last_name, user_name, email, password,
                phone, type, speciality, collage, previous_clincs,
                clinic_address, experience_years, certificate_number,
                child_name, child_age, parent_job, marriage_status,
                parent_gender, child_number, child_main_problem
        )
        loginObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LoginResponse> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(loginResponse: LoginResponse) {
                        if (loginResponse.success) registrationView.registrationSuccess(loginResponse.message) else registrationView.registrationFailed(loginResponse.message)
                    }

                    override fun onError(e: Throwable) {
                        registrationView.registrationFailed(e.message)
                    }

                    override fun onComplete() {}
                })
    }
}