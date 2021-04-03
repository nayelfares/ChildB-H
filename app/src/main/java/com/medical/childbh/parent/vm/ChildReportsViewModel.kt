package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.ChildrenResult
import com.medical.childbh.parent.model.ReportsResult
import com.medical.childbh.parent.ui.ChildReportsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ChildReportsViewModel(val context: Context,val childReportsView: ChildReportsView) {
    fun getReports(token:String,childId:Int) {
        val registerVar  = ParentApiManager.parentService.getReports(token, childId )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ReportsResult> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: ReportsResult) {
                        if (t.success)
                            childReportsView.getReportsSuccess(t.data)
                        else
                            childReportsView.getReportsFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        childReportsView.getReportsFailed(e.message.toString())
                    }
                })
    }
}