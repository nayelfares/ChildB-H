package com.medical.childbh.doctor.ui

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import com.bumptech.glide.Glide
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.doctor.vm.ProfileViewModel
import com.medical.childbh.parent.model.Doctor
import com.medical.childbh.toUrl
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.pick_date.*


class ProfileFragment : BaseFragment(R.layout.fragment_profile) ,ProfileView{
    lateinit var profileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel= ProfileViewModel(this,requireContext())
        photo1.setOnClickListener {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(requireActivity())
        }
        cameraContainer.setOnClickListener{photo1.callOnClick()}
        camera.setOnClickListener{photo1.callOnClick()}
        birthDate1.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    val dialog =
                            Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setCancelable(false)
                    dialog.setContentView(R.layout.pick_date)
                    dialog.datePickerCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.datePickerOk.setOnClickListener {
                        val pirthdateString=dialog.datePicker.year.toString()+"-"+(dialog.datePicker.month+1).toString()+"-"+dialog.datePicker.dayOfMonth.toString()
                        birthDate1.setText(pirthdateString)
                        dialog.dismiss()
                    }

                    val window: Window = dialog.window!!
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
                    dialog.show()
                }
            }

            v?.onTouchEvent(event) ?: true
        }

        submit1.setOnClickListener {
            loading()
            profileViewModel.updateProfie(
                    username1.text.toString(),
                    phone1.text.toString(),
                    birthDate1.text.toString(),
                    details1.text.toString(),
                    password1.text.toString()
            )
        }
        loading()
        profileViewModel.getProfile()

    }

    override fun getProfileSuccess(profile: Doctor) {
        stopLoading()
        Glide.with(requireContext())
            .load(profile.photo.toUrl())
            .into(photo1)
        username1.setText(profile.name)
        details1.setText(profile.details)
        email1.setText(profile.email)
        phone1.setText(profile.phone)
        birthDate1.setText(profile.dob)
    }

    override fun getProfileFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun updatePhotoOnSuccess(message: String, uri: Uri) {
        photo1.setImageURI(uri)
        stopLoading()
        showMessage(message)
    }

    override fun updatePhotoOnSFailer(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                loading()
                profileViewModel.updatePhoto(resultUri)
            }
        }
    }

    override fun updateProfileFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun updateProfileSuccess(message: String) {
        stopLoading()
        showMessage(message)
    }
}