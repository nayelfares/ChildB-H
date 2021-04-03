package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Report
import com.medical.childbh.parent.vm.ChildReportsViewModel
import com.medical.childbh.parent.vm.ReportAdapter
import kotlinx.android.synthetic.main.fragment_child_reports.*

class ChildReports(val childID:Int) : BaseFragment(R.layout.fragment_child_reports),ChildReportsView {
    lateinit var childReportsViewModel :ChildReportsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childReportsViewModel = ChildReportsViewModel(requireContext(),this)
        loading()
        childReportsViewModel.getReports(childID)
    }

    override fun getReportsSuccess(reports: ArrayList<Report>) {
        content.adapter = ReportAdapter(this,reports)
        stopLoading()
    }

    override fun getReportsFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}