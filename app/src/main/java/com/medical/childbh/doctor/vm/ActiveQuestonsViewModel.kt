package com.medical.childbh.doctor.vm

import android.content.Context
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.api.DoctorApiManager
import com.medical.childbh.doctor.ui.ActiveQuestonsView
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.ReportsResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ActiveQuestonsViewModel(val context: Context,val activeQuestonsView: ActiveQuestonsView) {
    fun getActiveQuestions() {
        val registerVar  = DoctorApiManager.doctorService.getActiveQuestions(DoctorActivity.token, DoctorActivity.id )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ReportsResult> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: ReportsResult) {
                        if (t.success)
                            activeQuestonsView.getActiveQuestionsSuccess(t.data)
                        else
                            activeQuestonsView.getActiveQuestionsFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        activeQuestonsView.getActiveQuestionsFailed(e.message.toString())
                    }
                })
    }
}