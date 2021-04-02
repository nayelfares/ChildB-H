package com.medical.childbh.onboarding.vm

import android.content.Context
import com.medical.childbh.onboarding.api.OnbordingApiManager
import com.medical.childbh.onboarding.model.LoginResponse
import com.medical.childbh.onboarding.ui.LoginView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(var context: Context, var loginView: LoginView) {
    fun login(email: String?, password: String?) {
        val loginObservable = OnbordingApiManager.onboardingService.login(email!!, password!!)
        loginObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LoginResponse> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(loginResponse: LoginResponse) {
                        if (loginResponse.success)
                            loginView.loginSuccess(loginResponse.data)
                        else
                            loginView.loginFailed(loginResponse.message)
                    }

                    override fun onError(e: Throwable) {
                        loginView.loginFailed(e.message.toString())
                    }

                    override fun onComplete() {}
                })
    }
}