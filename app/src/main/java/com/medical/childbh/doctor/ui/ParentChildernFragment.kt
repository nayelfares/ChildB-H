package com.medical.childbh.doctor.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.vm.ParentChildAdapter
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.model.Child
import com.medical.childbh.parent.ui.ChildrenView
import com.medical.childbh.parent.vm.ChildrenViewModel
import kotlinx.android.synthetic.main.fragment_parent_children.*

class ParentChildernFragment(val parentId:Int) : BaseFragment(R.layout.fragment_parent_children), ChildrenView {
    lateinit var childrenViewModel: ChildrenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childrenViewModel = ChildrenViewModel(requireContext(),this)
        loading()
        childrenViewModel.getChildren(DoctorActivity.token,parentId)
    }


    override fun getChildrenFailed(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun getChildrenSuccess(children: ArrayList<Child>) {
        stopLoading()
        content.adapter= ParentChildAdapter(this,children )
    }


}