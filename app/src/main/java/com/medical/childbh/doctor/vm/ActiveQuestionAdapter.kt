package com.medical.childbh.doctor.vm

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.medical.childbh.GeneralResponse
import com.medical.childbh.R
import com.medical.childbh.doctor.DoctorActivity
import com.medical.childbh.doctor.api.DoctorApiManager
import com.medical.childbh.doctor.ui.ActiveQuestons
import com.medical.childbh.parent.model.Report
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.active_qwestion_item_row.view.*


class ActiveQuestionAdapter(val context: ActiveQuestons, val reports:ArrayList<Report>) : RecyclerView.Adapter<ActiveQuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.active_qwestion_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.childName.text= report.child
        holder.question.text= report.question
        holder.addAnswerContainer.visibility= View.GONE
        holder.reply.visibility= View.VISIBLE
        holder.reply.text = context.resources.getString(R.string.reply)
        holder.newAnswer.setText("")
            holder.reply.setOnClickListener {
                if (holder.addAnswerContainer.visibility== View.GONE) {
                    holder.addAnswerContainer.visibility = View.VISIBLE
                    holder.reply.text = context.resources.getString(R.string.cancel)
                }else{
                    holder.addAnswerContainer.visibility = View.GONE
                    holder.reply.text = context.resources.getString(R.string.reply)
                }
            }

        holder.send.setOnClickListener {
            send(report.id,holder.newAnswer.text.toString(),position)
        }
    }

    private fun send(id: Int, reply: String,position: Int) {
        context.loading()
        val registerVar  = DoctorApiManager.doctorService.addAnswer(DoctorActivity.token,id,reply)
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success) {
                            reports.removeAt(position)
                            notifyItemRemoved(position)
                            notifyDataSetChanged()
                            context.stopLoading()
                        }
                        else{
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
        val question =itemView.question
        val reply  = itemView.reply
        val addAnswerContainer = itemView.addAnswerContainer
        val newAnswer  = itemView.newAnswer
        val send = itemView.send
    }
}