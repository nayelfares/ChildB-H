package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.medical.childbh.BaseFragment
import com.medical.childbh.GeneralResponse
import com.medical.childbh.R
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.Doctor
import com.medical.childbh.toUrl
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_doctor_details.*

class DoctorDetails(var doctor: Doctor) : BaseFragment(R.layout.fragment_doctor_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
                .load(doctor.photo.toUrl())
                .into(photo)
        username.text=doctor.name
        specialization.text=doctor.specialization
        address.text=doctor.address
        email.text=doctor.email
        phone.text=doctor.phone

        rating.onRatingBarChangeListener=object :RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                submit.visibility=View.VISIBLE
            }
    }

        submit.setOnClickListener {
            loading()
            val rateVar  = ParentApiManager.parentService.rate(ParentActivity.token,doctor.id,rating.rating.toInt(),ParentActivity.id)
            rateVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success) {
                            showMessage(t.message)
                            stopLoading()
                            submit.visibility = View.GONE
                            rating.setIsIndicator(true)
                        }
                        else {
                            showMessage(t.message)
                            stopLoading()
                        }
                    }
                    override fun onError(e: Throwable) {
                        showMessage(resources.getString(R.string.check_intenet_connection))
                        stopLoading()
                    }
                })
        }
    }
}