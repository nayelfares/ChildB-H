package com.medical.childbh.parent.ui

import com.medical.childbh.parent.model.Article

interface ArticalsView {
    fun onFailer(toString: String)
    fun onSuccess(categories: ArrayList<Article>)
}