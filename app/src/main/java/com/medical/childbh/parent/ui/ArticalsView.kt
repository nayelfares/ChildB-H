package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Article

interface ArticalsView {
    fun onFailer(message: String)
    fun onSuccess(categories: ArrayList<Article>)
}