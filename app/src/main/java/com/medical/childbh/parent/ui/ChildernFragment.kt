package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Child
import com.medical.childbh.parent.vm.ChildAdapter
import com.medical.childbh.parent.vm.ChildrenViewModel
import kotlinx.android.synthetic.main.activity_articals.*

class ChildrenFragment : BaseFragment(R.layout.fragment_children),ChildrenView {
    lateinit var childrenViewModel: ChildrenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childrenViewModel = ChildrenViewModel(requireContext(),this)
        loading()
        childrenViewModel.getChildren()
    }


    override fun getChildrenFailed(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun getChildrenSuccess(children: ArrayList<Child>) {
        stopLoading()
        content.adapter= ChildAdapter(requireContext(),children )
    }

}