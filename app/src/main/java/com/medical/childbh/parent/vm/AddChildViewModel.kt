package com.medical.childbh.parent.vm

import android.content.Context
import android.net.Uri
import com.medical.childbh.GeneralResponse
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.ArticleResult
import com.medical.childbh.parent.ui.AddChildView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File

class AddChildViewModel(val context: Context,val addChildView: AddChildView) {
    fun addChild( photo: Uri?,name: String,dob: String,information: String,gender: String
    ){
        var registerVar  = ParentApiManager.parentService.addChild(ParentActivity.token ,name,
                dob,information,gender,ParentActivity.id
        )
        if (photo!=null) {
            val picture = File(photo.path!!)
            val requestFile = picture.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("photo", picture.name, requestFile)
             registerVar  = ParentApiManager.parentService.addChild(ParentActivity.token ,body,name,
                    dob,information,gender,ParentActivity.id
            )
        }
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success)
                            addChildView.addChildSuccess(t.message)
                        else
                            addChildView.addChildFailer(t.message)
                    }
                    override fun onError(e: Throwable) {
                        addChildView.addChildFailer(e.message.toString())
                    }
                })
    }
}