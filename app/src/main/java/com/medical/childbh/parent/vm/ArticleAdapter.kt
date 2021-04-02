package com.medical.childbh.parent.vm

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medical.childbh.R
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.model.Article
import com.medical.childbh.parent.ui.ArticalsFragment
import com.medical.childbh.parent.ui.ArticleDetails
import com.medical.childbh.toUrl
import kotlinx.android.synthetic.main.category_item_row.view.*


class ArticleAdapter(val context: ArticalsFragment, val articles:ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.category_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
             holder.title.text=article.title
            holder.body.text=article.body
            Glide.with(context)
                .load(article.photo.toUrl())
                .into(holder.photo)
        if (position%2==0)
            holder.itemView.setBackgroundColor(context.requireContext().getColor(R.color.colorAccentTransparence))
        else
            holder.itemView.setBackgroundColor(context.requireContext().getColor(R.color.white))
        holder.itemView.setOnClickListener {
            (context.requireActivity() as ParentActivity).replaceFragment(ArticleDetails(article))
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return articles.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var photo=itemView.icon
        val title=itemView.title
        val body =itemView.body

    }
}