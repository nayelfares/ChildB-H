package com.medical.childbh.parent.ui

import android.os.Bundle
import android.view.View
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Article
import com.medical.childbh.parent.vm.ArticalsViewModel
import com.medical.childbh.parent.vm.ArticleAdapter
import kotlinx.android.synthetic.main.activity_articals.*

class ArticalsFragment : BaseFragment(R.layout.activity_articals),ArticalsView {
    lateinit var articalsViewModel: ArticalsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articalsViewModel= ArticalsViewModel(this,requireContext())
        loading()
        articalsViewModel.getCategories()
    }


    override fun onFailer(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun onSuccess(categories: ArrayList<Article>) {
        stopLoading()
        content.adapter= ArticleAdapter(requireContext(),categories)
    }

}