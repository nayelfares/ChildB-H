package com.medical.childbh.doctor.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.doctor.model.Parent
import com.medical.childbh.doctor.vm.ParentAdapter
import com.medical.childbh.doctor.vm.ParentsViewModel
import kotlinx.android.synthetic.main.fragment_parents.*
import java.util.ArrayList

class ParentsFragment : BaseFragment(R.layout.fragment_parents),ParentsView {
   lateinit var parentsViewModel: ParentsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentsViewModel= ParentsViewModel(requireContext(),this)
        loading()
        parentsViewModel.getParents()
    }

    override fun getParentsSuccess(parents: ArrayList<Parent>) {
        content.adapter = ParentAdapter(this,parents)
        stopLoading()
    }

    override fun getParentsFailed(message: String) {
        stopLoading()
        showMessage(message)
    }

}