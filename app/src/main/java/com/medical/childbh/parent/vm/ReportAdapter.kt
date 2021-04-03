package com.medical.childbh.parent.vm

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.medical.childbh.GeneralResponse
import com.medical.childbh.R
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.Report
import com.medical.childbh.parent.model.ReportsResult
import com.medical.childbh.parent.ui.ChildReports
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.report_item_row.view.*


class ReportAdapter(val context: ChildReports, val reports:ArrayList<Report>) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.report_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.childName.text= report.child
        holder.doctorName.text= report.doctor
        holder.question.text= report.question
        holder.answer.text= report.answer
        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context.requireContext())
            builder.setTitle(context.requireContext().resources.getString(R.string.app_name))
            builder.setMessage(context.requireContext().getString(R.string.do_you_want_to_delete_consultation))
            builder.setPositiveButton(context.requireContext().getString(R.string.yes)) { _, _ ->
                delete(report.id,position)
            }
            builder.setNegativeButton(context.requireContext().getString(R.string.no)) { _, _ ->

            }
            builder.show()

            true
        }
    }

    private fun delete(id: Int, position: Int) {
        context.loading()
            val registerVar  = ParentApiManager.parentService.deleteReport(ParentActivity.token, id )
            registerVar.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<GeneralResponse> {
                        override fun onComplete() {}
                        override fun onSubscribe(d: Disposable) {}
                        override fun onNext(t: GeneralResponse) {
                            if (t.success) {
                                reports.removeAt(position)
                                notifyItemRemoved(position)
                                notifyDataSetChanged()
                                context.stopLoading()
                            } else {
                                context.stopLoading()
                               context.showMessage(t.message)
                            }
                        }

                        override fun onError(e: Throwable) {
                            context.stopLoading()
                            context.showMessage(e.message.toString())
                        }
                    })

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