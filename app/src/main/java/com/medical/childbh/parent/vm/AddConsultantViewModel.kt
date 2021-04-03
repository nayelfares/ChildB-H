package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.GeneralResponse
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.DoctorsListResult
import com.medical.childbh.parent.ui.AddConsultantView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddConsultantViewModel(val context: Context,val addConsultantView: AddConsultantView) {
    fun getDoctorsList(){
        val registerVar  = ParentApiManager.parentService.getDoctorsList(ParentActivity.token )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<DoctorsListResult> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: DoctorsListResult) {
                        if (t.success)
                            addConsultantView.getDoctorsListSuccess(t.data)
                        else
                            addConsultantView.getDoctorsListFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        addConsultantView.getDoctorsListFailed(e.message.toString())
                    }
                })
    }

    fun addConsultation(childId:Int,doctorId:Int,question:String){
        val registerVar  = ParentApiManager.parentService.addConsultation(ParentActivity.token,childId,doctorId,question )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success)
                            addConsultantView.addConsultationSuccess(t.message)
                        else
                            addConsultantView.addConsultationFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        addConsultantView.addConsultationFailed(e.message.toString())
                    }
                })
    }
}