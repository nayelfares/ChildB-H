package com.medical.childbh.onboarding.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.onboarding.vm.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_trainer_registration.*

class TrainerRegistration(
        var firstname   : String,
        var lastname    : String,
        var username    : String,
        var email       : String,
        var password    : String,
        var mobileNumber: String
        ) : BaseFragment(R.layout.fragment_trainer_registration), RegistrationView {
    lateinit var registrationViewModel: RegistrationViewModel
    var type = "trainer"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel = RegistrationViewModel(this, requireContext())
        register.setOnClickListener {
            if (conditions.isChecked()) {
                loading()
                registrationViewModel.register(
                        firstname, lastname, username, email, password, mobileNumber, type,
                        speciality.getText().toString(),
                        collage.getText().toString(),
                        previous_clincs.getText().toString(),
                        clinic_address.getText().toString(),
                        experience_years.getText().toString(),
                        certificate_number.getText().toString(),
                        "", "", "", "", "", "", ""
                )
            } else showMessage(requireContext().resources.getString(R.string.approve_conditions_first))
        }
    }

    override fun registrationSuccess(message: String) {
        stopLoading()
        showMessage(message)
        val fragment = requireActivity().supportFragmentManager.fragments[0] as LoginFragment
        fragment.emailEditText.setText(email)
        fragment.passwordEditText.setText(password)
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun registrationFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}