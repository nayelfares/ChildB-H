package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.GeneralResponse
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.ui.UpdateChildView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UpdateChildViewModel(val context: Context,val updateChildView: UpdateChildView) {
    fun updateChild(id:Int,name: String,  dob: String,information: String, gender: String) {
        val registerVar  = ParentApiManager.parentService.updateChild(ParentActivity.token ,id,name,
                dob,information,gender, ParentActivity.id
        )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success)
                            updateChildView.updateChildSuccess(t.message)
                        else
                            updateChildView.updateChildFailer(t.message)
                    }
                    override fun onError(e: Throwable) {
                        updateChildView.updateChildFailer(e.message.toString())
                    }
                })
    }
}