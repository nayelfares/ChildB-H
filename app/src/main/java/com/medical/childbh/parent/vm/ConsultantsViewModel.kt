package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.DoctorsListResult
import com.medical.childbh.parent.ui.ConsultantsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ConsultantsViewModel(val consultantsView: ConsultantsView, val context: Context) {
    fun getDoctorsList(){
        val registerVar  = ParentApiManager.parentService.getDoctorsList(ParentActivity.token )
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DoctorsListResult> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: DoctorsListResult) {
                    consultantsView.onSuccess(t.data)
                }
                override fun onError(e: Throwable) {
                    consultantsView.onFailer(e.message.toString())
                }
            })
    }
}