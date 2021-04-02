package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Doctor
import com.medical.childbh.parent.vm.ConsultantsViewModel
import com.medical.childbh.parent.vm.DoctorAdapter
import kotlinx.android.synthetic.main.activity_articals.*

class DoctorsFragment : BaseFragment(R.layout.fragment_doctors),ConsultantsView {
    lateinit var consultantsViewModel: ConsultantsViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultantsViewModel= ConsultantsViewModel(this,requireContext())
        loading()
        consultantsViewModel.getDoctorsList()
    }

    override fun onFailer(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun onSuccess(doctors: ArrayList<Doctor>) {
        stopLoading()
        content.adapter= DoctorAdapter(this,doctors)
    }

}