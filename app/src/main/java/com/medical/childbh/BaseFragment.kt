package com.medical.childbh

import android.app.Dialog
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    var dialog: Dialog? = null
    fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun loading() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.loading)
        dialog!!.show()
    }

    fun stopLoading() {
        dialog!!.dismiss()
    }
}