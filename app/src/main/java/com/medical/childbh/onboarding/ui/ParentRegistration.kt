package com.medical.childbh.onboarding.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.onboarding.vm.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_parent_registration.*

class ParentRegistration(var firstname: String, var lastname: String, var username: String, var email: String, var password: String, var mobileNumber: String) : BaseFragment(R.layout.fragment_parent_registration), RegistrationView {
    lateinit var registrationViewModel: RegistrationViewModel
    var type = "parent"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel = RegistrationViewModel(this, requireContext())
        val items = arrayOf<String?>("Married", "widow", "Divorced")
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        marriage_status.setAdapter(adapter)
        val genders = arrayOf<String?>("Male", "Female")
        val genderAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        parent_gender.setAdapter(genderAdapter)
        register.setOnClickListener(View.OnClickListener { v: View? ->
            if (conditions.isChecked()) {
                loading()
                registrationViewModel.register(
                        firstname, lastname, username, email, password, mobileNumber, type,
                        "", "", "", "", "", "",
                        child_name.text.toString(),
                        child_age.text.toString(),
                        parent_job.text.toString(),
                        marriage_status.selectedItem.toString(),
                        parent_gender.selectedItem.toString(),
                        child_number.text.toString(),
                        child_main_problem.text.toString()
                )
            } else showMessage(requireContext().resources.getString(R.string.approve_conditions_first))
        })
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