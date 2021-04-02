package com.medical.childbh.parent.ui


import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.medical.childbh.BaseFragment
import com.medical.childbh.R
import com.medical.childbh.parent.model.Article
import com.medical.childbh.toUrl
import kotlinx.android.synthetic.main.fragment_article_details.*

class ArticleDetails(var currentLocation: Article) : BaseFragment(R.layout.fragment_article_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name.text=currentLocation.title
        description.text=currentLocation.body
        Glide.with(requireContext())
                .load(currentLocation.photo.toUrl())
                .into(photoView)
    }
}