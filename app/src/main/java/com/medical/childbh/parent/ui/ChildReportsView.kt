package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Report

interface ChildReportsView {
    fun getReportsSuccess(reports: ArrayList<Report>)
    fun getReportsFailed(message: String)

}