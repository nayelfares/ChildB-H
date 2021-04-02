package com.medical.childbh

import android.app.Dialog
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

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



     fun String.isValidEmail(): Boolean {
         val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
                 "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                         "\\@" +
                         "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                         "(" +
                         "\\." +
                         "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                         ")+"
         )
        return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
    }
}