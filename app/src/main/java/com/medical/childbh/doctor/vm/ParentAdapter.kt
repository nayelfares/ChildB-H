package com.medical.childbh.doctor.vm

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medical.childbh.R
import com.medical.childbh.doctor.model.Parent
import com.medical.childbh.doctor.ui.ParentsFragment

class ParentAdapter(val context: ParentsFragment, val parents: ArrayList<Parent>) : RecyclerView.Adapter<ParentAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.parent_item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val parent = parents[position]
        viewHolder.name.setText(parent.name)
        viewHolder.phone.setText(parent.phone)
        viewHolder.itemView.setOnClickListener {
          //  (context.requireActivity() as ParentActivity).replaceFragment(UpdateChild(context,parent))
        }
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name     : TextView             = view.findViewById(R.id.name)
        val phone     : TextView = view.findViewById(R.id.phone)

    }



}