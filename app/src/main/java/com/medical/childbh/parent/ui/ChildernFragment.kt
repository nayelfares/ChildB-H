package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.model.Child
import com.medical.childbh.parent.vm.ChildAdapter
import com.medical.childbh.parent.vm.ChildrenViewModel
import kotlinx.android.synthetic.main.activity_articals.*
import kotlinx.android.synthetic.main.activity_articals.content
import kotlinx.android.synthetic.main.fragment_children.*

class ChildrenFragment : BaseFragment(R.layout.fragment_children),ChildrenView {
    lateinit var childrenViewModel: ChildrenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childrenViewModel = ChildrenViewModel(requireContext(),this)
        loading()
        childrenViewModel.getChildren()
        addChild.setOnClickListener {
            (requireActivity() as ParentActivity).replaceFragment(AddChild())
        }
    }


    override fun getChildrenFailed(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun getChildrenSuccess(children: ArrayList<Child>) {
        stopLoading()
        content.adapter= ChildAdapter(this,children )
    }

}