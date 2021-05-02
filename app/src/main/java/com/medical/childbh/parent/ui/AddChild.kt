package com.medical.childbh.parent.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.ActionBar
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.vm.AddChildViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_add_child.*


class AddChild(val childrenFragment: ChildrenFragment): BaseFragment(R.layout.fragment_add_child),AddChildView {
    lateinit var addChildViewModel:AddChildViewModel
    var photoUri: Uri?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addChildViewModel = AddChildViewModel(requireContext(), this)
        events()
    }

    fun events(){
        birthDate.setOnTouchListener(OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val dialog = Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.pick_date)
                dialog.findViewById<View>(R.id.datePickerCancel).setOnClickListener { v: View? -> dialog.dismiss() }
                dialog.findViewById<View>(R.id.datePickerOk).setOnClickListener { v: View? ->
                    val datePicker = dialog.findViewById<DatePicker>(R.id.datePicker)
                    val pirthdateString = datePicker.year.toString() + "-" + (datePicker.month + 1) + "-" + datePicker.dayOfMonth
                    birthDate.setText(pirthdateString)
                    dialog.dismiss()
                }
                val window = dialog.window
                window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
                dialog.show()
            }
            false
        })
        val genders = arrayOf("Male", "Female")
        val genderAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        gender.setAdapter(genderAdapter)

        photo.setOnClickListener {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(requireActivity())
        }
        choosePhoto.setOnClickListener { photo.callOnClick() }

        submit.setOnClickListener {
                loading()
                addChildViewModel.addChild(
                        photoUri,
                        name.text.toString(),
                        birthDate.text.toString(),
                        information.text.toString(),
                        gender.selectedItem.toString()
                )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                photoUri = result.uri
                photo.setImageURI(photoUri)
            }
        }
    }

    override fun addChildSuccess(message: String) {
        stopLoading()
        showMessage(message)
        childrenFragment.reload()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun addChildFailer(message: String) {
        stopLoading()
        showMessage(message)
    }
}