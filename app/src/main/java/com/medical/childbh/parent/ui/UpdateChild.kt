package com.medical.childbh.parent.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Child
import com.medical.childbh.parent.vm.UpdateChildViewModel
import com.medical.childbh.toUrl
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_add_child.*


class UpdateChild(val childrenFragment: ChildrenFragment,val child: Child) : BaseFragment(R.layout.fragment_update_child),UpdateChildView {
    lateinit var updateChildViewModel: UpdateChildViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateChildViewModel= UpdateChildViewModel(requireContext(),this)
        events()
        name.setText( child.name)
        birthDate.setText( child.dob)
        information.setText( child.information)
        if (child.gender=="male")
            gender.setSelection(0)
        else
            gender.setSelection(1)
        Glide.with(requireContext())
                .load(child.photo.toUrl())
                .into(photo)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun events(){
        birthDate.setOnTouchListener{ _, motionEvent ->
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
        }
        val genders = arrayOf("Male", "Female")
        val genderAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        gender.setAdapter(genderAdapter)

        submit.setOnClickListener {
            loading()
            updateChildViewModel.updateChild(
                    child.id,
                        name.text.toString(),
                        birthDate.text.toString(),
                        information.text.toString(),
                        gender.selectedItem.toString()
                )
        }
    }

    override fun updateChildSuccess(message: String) {
        stopLoading()
        showMessage(message)
        childrenFragment.reload()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun updateChildFailer(message: String) {
        stopLoading()
        showMessage(message)
    }
}