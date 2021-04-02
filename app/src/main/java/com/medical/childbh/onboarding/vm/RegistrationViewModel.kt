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
            email              : String,
            password           : String,
            phone              : String,
            type               : String,
            speciality         : String,
            address            : String,
    ) {
        val loginObservable = OnbordingApiManager.onboardingService.register(
                first_name, email, password,
                phone, type, speciality, address
        )
        loginObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LoginResponse> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(loginResponse: LoginResponse) {
                        if (loginResponse.success) registrationView.registrationSuccess(loginResponse.message) else registrationView.registrationFailed(loginResponse.message)
                    }

                    override fun onError(e: Throwable) {
                        registrationView.registrationFailed(e.message.toString())
                    }

                    override fun onComplete() {}
                })
    }
}