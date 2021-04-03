package com.medical.childbh.doctor.vm

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medical.childbh.R
import com.medical.childbh.doctor.ui.ParentChildernFragment
import com.medical.childbh.parent.model.Child
import com.medical.childbh.toUrl
import de.hdodenhof.circleimageview.CircleImageView


class ParentChildAdapter(val context: ParentChildernFragment, val children: ArrayList<Child>) : RecyclerView.Adapter<ParentChildAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.parent_child_item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val child = children[position]
        viewHolder.name.setText(child.name)
        Glide.with(context)
                .load(child.photo.toUrl())
                .into(viewHolder.image)

        viewHolder.itemView.setOnClickListener {
         //   (context.requireActivity() as ParentActivity).replaceFragment(UpdateChild(context,child))
        }
    }

    override fun getItemCount(): Int {
        return children.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name     : TextView             = view.findViewById(R.id.name)
        val image    : CircleImageView      = view.findViewById(R.id.photo)

    }


}