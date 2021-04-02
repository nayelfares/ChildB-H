package com.medical.childbh.parent.vm

import android.content.Context
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.ArticleResult
import com.medical.childbh.parent.ui.ArticalsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticalsViewModel(val articalsView: ArticalsView, val context: Context) {
    fun getCategories(){
        val registerVar  = ParentApiManager.parentService.getArticleList(ParentActivity.token )
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ArticleResult> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: ArticleResult) {
                    if (t.success)
                        articalsView.onSuccess(t.data)
                    else
                        articalsView.onFailer(t.message)
                }
                override fun onError(e: Throwable) {
                    articalsView.onFailer(e.message.toString())
                }
            })
    }
}