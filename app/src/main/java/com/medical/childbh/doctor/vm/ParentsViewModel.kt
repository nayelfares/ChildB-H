package com.medical.childbh.doctor.vm

import android.content.Context
import com.medical.childbh.R
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.api.DoctorApiManager
import com.medical.childbh.doctor.model.ParentsResponse
import com.medical.childbh.doctor.ui.ParentsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ParentsViewModel(val context: Context,val parentsView: ParentsView) {
    fun getParents(){
        val registerVar  = DoctorApiManager.doctorService.getParents(DoctorActivity.token)
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ParentsResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: ParentsResponse) {
                        if (t.success)
                            parentsView.getParentsSuccess(t.data)
                        else
                            parentsView.getParentsFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        parentsView.getParentsFailed(context.resources.getString(R.string.check_intenet_connection))
                    }
                })
    }
}