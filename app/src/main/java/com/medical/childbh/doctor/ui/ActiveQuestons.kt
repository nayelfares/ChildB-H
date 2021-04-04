package com.medical.childbh.doctor.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.doctor.vm.ActiveQuestionAdapter
import com.medical.childbh.doctor.vm.ActiveQuestonsViewModel
import com.medical.childbh.parent.model.Report
import kotlinx.android.synthetic.main.fragment_active_questons.*


class ActiveQuestons : BaseFragment(R.layout.fragment_active_questons),ActiveQuestonsView {
    lateinit var activeQuestonsViewModel: ActiveQuestonsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activeQuestonsViewModel= ActiveQuestonsViewModel(requireContext(),this)
        loading()
        activeQuestonsViewModel.getActiveQuestions()
    }

    override fun getActiveQuestionsSuccess(data: ArrayList<Report>) {
        content.adapter=ActiveQuestionAdapter(this,data)
        stopLoading()
    }

    override fun getActiveQuestionsFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}