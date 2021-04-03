package com.medical.childbh.parent.vm

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medical.childbh.R
import com.medical.childbh.parent.model.Doctor
import com.medical.childbh.parent.ui.AddConsultant
import com.medical.childbh.toUrl
import kotlinx.android.synthetic.main.consultant_item_row.view.*


class ConsultantAdapter(val context: AddConsultant, val doctors:ArrayList<Doctor>) : RecyclerView.Adapter<ConsultantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.consultant_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.name.text=doctor.name
        holder.specialization.text=doctor.specialization
        Glide.with(context)
                .load(doctor.photo.toUrl())
                .into(holder.photo)
        if (doctor.selected)
            holder.itemView.setBackgroundColor(context.requireContext().getColor(R.color.colorAccentTransparence))
        else
            holder.itemView.setBackgroundColor(context.requireContext().getColor(R.color.white))
        holder.itemView.setOnClickListener {
            for((i,doc) in doctors.withIndex()){
                if (doctors[i].selected){
                    doctors[i].selected =false
                    notifyItemChanged(i)
                }
            }
            doctors[position].selected =true
            notifyItemChanged(position)
            context.select(doctor.id)
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return doctors.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var photo=itemView.image
        val name=itemView.name
        val specialization =itemView.specialization
    }
}