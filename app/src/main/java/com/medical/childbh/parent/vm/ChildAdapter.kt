package com.medical.childbh.parent.vm

import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medical.childbh.GeneralResponse
import com.medical.childbh.R
import com.medical.childbh.parent.ParentActivity
import com.medical.childbh.parent.api.ParentApiManager
import com.medical.childbh.parent.model.Child
import com.medical.childbh.parent.ui.ChildrenFragment
import com.medical.childbh.parent.ui.UpdateChild
import com.medical.childbh.toUrl
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ChildAdapter(val context: ChildrenFragment, val children: ArrayList<Child>) : RecyclerView.Adapter<ChildAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val child = children[position]
        viewHolder.name.setText(child.name)
        Glide.with(context)
                .load(child.photo.toUrl())
                .into(viewHolder.image)
        viewHolder.more.setOnClickListener(View.OnClickListener { v: View? ->
            val popup = PopupMenu(context.requireContext(), viewHolder.more)
            //inflating menu from xml resource
            popup.inflate(R.menu.child_options)
            //adding click listener
            popup.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.addConsultant -> {

                        return@setOnMenuItemClickListener true
                    }
                    R.id.delete -> {
                        context.loading()
                        deleteChild(child.id,position)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.viewReoprt -> {

                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popup.show()
        })

        viewHolder.itemView.setOnClickListener {
            (context.requireActivity() as ParentActivity).replaceFragment(UpdateChild(context,child))
        }
    }

    override fun getItemCount(): Int {
        return children.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name     : TextView             = view.findViewById(R.id.name)
        val more     : AppCompatImageButton = view.findViewById(R.id.more)
        val image    : CircleImageView      = view.findViewById(R.id.photo)

    }


    fun deleteChild(childId:Int,position:Int){
        val registerVar  = ParentApiManager.parentService.deleteChild(ParentActivity.token ,childId)
        registerVar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GeneralResponse> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: GeneralResponse) {
                        if (t.success) {
                            context.stopLoading()
                            children.removeAt(position)
                            notifyItemRemoved(position)
                            notifyDataSetChanged()
                        } else {
                            context.stopLoading()
                            context.showMessage(t.message)
                        }
                    }

                    override fun onError(e: Throwable) {
                        context.stopLoading()
                        context.showMessage(e.message)
                    }
                })
    }

}