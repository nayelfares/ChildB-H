package com.medical.childbh.onboarding.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.onboarding.OnboardingProcess
import kotlinx.android.synthetic.main.fragment_general_register.*

class GeneralRegister : BaseFragment(R.layout.fragment_general_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parent.isChecked = true
        next.setOnClickListener {
            if (email.text.toString().isValidEmail()) {
                if (password.text.toString()!="") {
                    if (doctor.isChecked) {
                        (requireActivity() as OnboardingProcess).replaceFragment(DoctorRegistration(
                                name.text.toString(),
                                email.text.toString(),
                                password.text.toString(),
                                mobileNumber.text.toString()
                        ))
                    }
                    if (parent.isChecked) {
                        (requireActivity() as OnboardingProcess).replaceFragment(ParentRegistration(
                                name.text.toString(),
                                email.text.toString(),
                                password.text.toString(),
                                mobileNumber.text.toString()
                        ))
                    }
                }else{
                    showMessage(requireContext().resources.getString(R.string.blank_passowrd))
                }
            }else{
                showMessage(requireContext().resources.getString(R.string.type_valid_email))
            }
        }
    }
}