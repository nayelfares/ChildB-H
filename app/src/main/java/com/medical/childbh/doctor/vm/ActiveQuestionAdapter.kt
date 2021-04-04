package com.medical.childbh.doctor.vm

import android.content.Context
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
import com.medical.childbh.doctor.model.ParentsResponse
import com.medical.childbh.parent.model.Report
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.active_qwestion_item_row.view.*


class ActiveQuestionAdapter(val context: Context, val reports:ArrayList<Report>) : RecyclerView.Adapter<ActiveQuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.active_qwestion_item_row, parent, false))
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.childName.text= report.child
        holder.doctorName.text= report.doctor
        holder.question.text= report.question
        holder.answer.text= report.answer
        holder.addAnswerContainer.visibility= View.GONE
        holder.reply.visibility= View.GONE
        holder.doctorAnswered.visibility= View.GONE
        if (report.answer==""){
            holder.reply.visibility= View.VISIBLE
            holder.reply.setOnClickListener {
                if (holder.addAnswerContainer.visibility== View.GONE) {
                    holder.addAnswerContainer.visibility = View.VISIBLE
                    holder.reply.text = context.resources.getString(R.string.cancel)
                }else{
                    holder.addAnswerContainer.visibility = View.GONE
                    holder.reply.text = context.resources.getString(R.string.reply)
                }
            }
        }else{
            holder.doctorAnswered.visibility= View.VISIBLE
        }

        holder.send.setOnClickListener {
            send(report.id,holder.newAnswer.text.toString(),position)
        }
    }

    private fun send(id: Int, reply: String,position: Int) {
        val registerVar  = DoctorApiManager.doctorService.addAnswer(DoctorActivity.token,id,reply)
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() { }
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(t: GeneralResponse) {
                        if (t.success) {
                            reports[position].answer=reply
                            notifyItemChanged(position)
                        }
                        else{

                        }
                    }
                    override fun onError(e: Throwable) { }
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
        val reply  = itemView.reply
        val addAnswerContainer = itemView.addAnswerContainer
        val doctorAnswered = itemView.doctorAnswered
        val newAnswer  = itemView.newAnswer
        val send = itemView.send
    }
}