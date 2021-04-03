package com.medical.childbh.doctor.ui

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
        photo.setOnClickListener {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(requireActivity())
        }
        cameraContainer.setOnClickListener{photo.callOnClick()}
        camera.setOnClickListener{photo.callOnClick()}


        submit1.setOnClickListener {
            loading()
            profileViewModel.updateProfie(
                    username.text.toString(),
                    phone.text.toString(),
                    address.text.toString(),
                    specialization.text.toString()
            )
        }
        loading()
        profileViewModel.getProfile()

    }

    override fun getProfileSuccess(profile: Doctor) {
        Log.e("photo",profile.photo)
        stopLoading()
        Glide.with(requireContext())
            .load(profile.photo.toUrl())
            .into(photo)
        username.setText(profile.name)
        phone.setText(profile.phone)
        email.setText(profile.email)
        address.setText(profile.address)
        specialization.setText(profile.specialization)
    }

    override fun getProfileFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun updatePhotoOnSuccess(message: String, uri: Uri) {
        photo.setImageURI(uri)
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