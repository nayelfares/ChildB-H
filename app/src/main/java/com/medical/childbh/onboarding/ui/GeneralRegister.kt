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
        next.setOnClickListener {
            if (trainer.isChecked()) {
                (requireActivity() as OnboardingProcess).replaceFragment(TrainerRegistration(
                        firstname.text.toString(),
                        lastname.text.toString(),
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        mobileNumber.getText().toString()
                ))
            }
            if (parent.isChecked()) {
                (requireActivity() as OnboardingProcess).replaceFragment(ParentRegistration(
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        mobileNumber.getText().toString()
                ))
            }
        }
    }
}