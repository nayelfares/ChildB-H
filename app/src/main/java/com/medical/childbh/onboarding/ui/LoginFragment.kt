package com.medical.childbh.onboarding.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.onboarding.OnboardingProcess
import com.medical.childbh.onboarding.model.LoginData
import com.medical.childbh.onboarding.vm.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(R.layout.fragment_login), LoginView {
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginViewModel: LoginViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = LoginViewModel(requireContext(), this)
        this.emailEditText = email
        this.passwordEditText = password;
        login.setOnClickListener{
            loading()
            loginViewModel.login(email.getText().toString(), password.getText().toString())
        }
        register.setOnClickListener{
            (requireActivity() as OnboardingProcess).replaceFragment(GeneralRegister())
        }
    }

    override fun loginSuccess(data: LoginData) {
        if (data.type == "parent") {
//            ParentActivity.id = data.id;
//            ParentActivity.token = "Bearer " + data.token;
//            ParentActivity.type = data.type;
//            requireActivity().startActivity(new Intent(requireContext(), ParentActivity.class));
        } else {
//            TrainerActivity.id = data.id;
//            TrainerActivity.token = "Bearer " + data.token;
//            TrainerActivity.type = data.type;
//            requireActivity().startActivity(new Intent(requireContext(), TrainerActivity.class));
        }
        stopLoading()
        requireActivity().finish()
    }

    override fun loginFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}