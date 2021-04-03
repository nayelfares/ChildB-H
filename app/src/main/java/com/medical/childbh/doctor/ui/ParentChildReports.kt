package com.medical.childbh.doctor.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.vm.DoctorReportAdapter
import com.medical.childbh.parent.model.Report
import com.medical.childbh.parent.ui.ChildReportsView
import com.medical.childbh.parent.vm.ChildReportsViewModel
import kotlinx.android.synthetic.main.fragment_child_reports.*

class ParentChildReports(val childID:Int) : BaseFragment(R.layout.fragment_child_reports), ChildReportsView {
    lateinit var childReportsViewModel :ChildReportsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childReportsViewModel = ChildReportsViewModel(requireContext(),this)
        loading()
        childReportsViewModel.getReports(DoctorActivity.token,childID)
    }

    override fun getReportsSuccess(reports: ArrayList<Report>) {
        content.adapter = DoctorReportAdapter(reports)
        stopLoading()
    }

    override fun getReportsFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}