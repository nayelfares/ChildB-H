package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.ChildrenResult
import com.medical.childbh.parent.ui.ChildrenView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ChildrenViewModel(val context: Context,val childrenView: ChildrenView) {
    fun getChildren(token:String,parentId:Int) {
        val registerVar  = ParentApiManager.parentService.getChildren(token,parentId )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ChildrenResult> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: ChildrenResult) {
                        if (t.success)
                            childrenView.getChildrenSuccess(t.data)
                        else
                            childrenView.getChildrenFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        childrenView.getChildrenFailed(e.message.toString())
                    }
                })
    }
}