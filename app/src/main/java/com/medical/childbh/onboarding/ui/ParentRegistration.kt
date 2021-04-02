package com.medical.childbh.onboarding.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.onboarding.vm.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_parent_registration.*

class ParentRegistration(var name: String, var email: String, var password: String, var mobileNumber: String) : BaseFragment(R.layout.fragment_parent_registration), RegistrationView {
    lateinit var registrationViewModel: RegistrationViewModel
    var type = "parent"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel = RegistrationViewModel(this, requireContext())

        register.setOnClickListener{
            if (conditions.isChecked) {
                loading()
                registrationViewModel.register(name, email, password, mobileNumber, type,
                        "",address.text.toString()
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