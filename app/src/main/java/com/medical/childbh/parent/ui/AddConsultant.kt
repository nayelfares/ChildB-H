package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Doctor
import com.medical.childbh.parent.vm.AddConsultantViewModel
import com.medical.childbh.parent.vm.ConsultantAdapter
import kotlinx.android.synthetic.main.fragment_add_consultant.*
import java.util.ArrayList


class AddConsultant(val childId:Int) : BaseFragment(R.layout.fragment_add_consultant),AddConsultantView {
    lateinit var addConsultantViewModel :AddConsultantViewModel
    var doctorId = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addConsultantViewModel = AddConsultantViewModel(requireContext(),this)
        loading()
        addConsultantViewModel.getDoctorsList()
        submit.setOnClickListener {
            if (doctorId!=-1) {
                loading()
                addConsultantViewModel.addConsultation(childId, doctorId, question.text.toString())
            }else{
                showMessage(requireContext().resources.getString(R.string.choose_doctor_from_list))
            }
        }
    }
    fun select(doctorId: Int) {
        this.doctorId = doctorId
    }

    override fun getDoctorsListSuccess(doctors: ArrayList<Doctor>) {
        stopLoading()
        doctorsContent.adapter = ConsultantAdapter(this,doctors)
    }

    override fun getDoctorsListFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun addConsultationSuccess(message: String) {
        stopLoading()
        showMessage(message)
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun addConsultationFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

}