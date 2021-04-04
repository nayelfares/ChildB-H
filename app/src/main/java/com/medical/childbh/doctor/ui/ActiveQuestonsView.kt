package com.medical.childbh.doctor.ui

import com.medical.childbh.parent.model.Report

interface ActiveQuestonsView {
    fun getActiveQuestionsSuccess(data: ArrayList<Report>)
    fun getActiveQuestionsFailed(message: String)
}