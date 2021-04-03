package com.medical.childbh.doctor.vm

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.medical.childbh.R
import com.medical.childbh.parent.model.Report
import kotlinx.android.synthetic.main.doctor_report_item_row.view.*


class DoctorReportAdapter( val reports:ArrayList<Report>) : RecyclerView.Adapter<DoctorReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.doctor_report_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.childName.text= report.child
        holder.doctorName.text= report.doctor
        holder.question.text= report.question
        holder.answer.text= report.answer
    }


    // total number of rows
    override fun getItemCount(): Int {
        return reports.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var childName=itemView.childName
        val doctorName=itemView.doctorName
        val question =itemView.question
        val answer =itemView.answer
    }
}