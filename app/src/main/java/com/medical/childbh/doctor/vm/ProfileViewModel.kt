package com.medical.childbh.doctor.vm

import android.content.Context
import android.net.LinkAddress
import android.net.Uri
import com.medical.childbh.GeneralResponse
import com.medical.childbh.R
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.api.DoctorApiManager
import com.medical.childbh.doctor.model.ProfileResponse
import com.medical.childbh.doctor.ui.ProfileView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileViewModel(val profileView: ProfileView, val context: Context) {
    fun getProfile(){
        val registerVar  = DoctorApiManager.doctorService.getProfile(DoctorActivity.token,DoctorActivity.id)
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ProfileResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: ProfileResponse) {
                        if (t.success)
                            profileView.getProfileSuccess(t.data)
                        else
                            profileView.getProfileFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        profileView.getProfileFailed(context.resources.getString(R.string.check_intenet_connection))
                    }
                })
    }

    fun updateProfie(name:String,phone:String,address:String,specialization:String){
        val registerVar  = DoctorApiManager.doctorService.updateProfile(
                DoctorActivity.token,
                DoctorActivity.id,
                name,phone,address,specialization
        )
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success)
                            profileView.updateProfileSuccess(t.message)
                        else
                            profileView.updateProfileFailed(t.message)
                    }
                    override fun onError(e: Throwable) {
                        profileView.updateProfileFailed(context.resources.getString(R.string.check_intenet_connection))
                    }
                })

    }

    fun updatePhoto(uri: Uri){
        val picture = File(uri.path!!)
        var requestFile=picture.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("photo", picture.name, requestFile)
        val loginVar  = DoctorApiManager.doctorService.upload(DoctorActivity.token,body,DoctorActivity.id)
        loginVar?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GeneralResponse?> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success) {
                            profileView.updatePhotoOnSuccess(t.message,uri)
                        }
                        else
                            profileView.updatePhotoOnSFailer(t.message)
                    }
                    override fun onError(e: Throwable) {
                        profileView.updatePhotoOnSFailer(context.resources.getString(R.string.check_intenet_connection))
                    }
                })
    }


}