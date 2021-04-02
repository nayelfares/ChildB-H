package com.medical.childbh.parent.ui

import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.Window
import android.widget.DatePicker
import androidx.appcompat.app.ActionBar
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.vm.AddChildViewModel
import kotlinx.android.synthetic.main.fragment_add_child.*


class AddChild : BaseFragment(R.layout.fragment_add_child),AddChildView {
 lateinit var addChildViewModel:AddChildViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addChildViewModel = AddChildViewModel(requireContext(), this)
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
    }
}