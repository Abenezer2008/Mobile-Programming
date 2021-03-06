package edu.miu.cvBuilder.adapter

import CVBuilderApp.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvBuilder.domain.Work

class WorkViewAdapter(val context: Context, val workList: MutableList<Work>) :
    RecyclerView.Adapter<MainView?>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MainView {
        val itemEvents: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.work_unit, viewGroup, false)
        return AssignedTasksView(itemEvents)
    }

    fun addWork(work: Work){
        workList.add(work)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(mainView: MainView, i: Int) {
        mainView.onBind(i)
    }

    override fun getItemCount(): Int = workList.size

    inner class AssignedTasksView(view: View?) : MainView(view) {
        var title: TextView? = view?.findViewById(R.id.rv_work_title)
        var image: ImageView? = view?.findViewById(R.id.rv_work_logo)
        var workPosition: TextView? = view?.findViewById(R.id.rv_work_position)
        var duration: TextView? = view?.findViewById(R.id.rv_work_duration)
        var description: TextView? = view?.findViewById(R.id.rv_work_description)

        @SuppressLint("NotifyDataSetChanged")
        override fun onBind(position: Int) {
            super.onBind(position)
            val product = workList[position]

            image?.setBackgroundResource(product.image)
            title?.text = product.title
            workPosition?.text = product.position
            duration?.text = product.duration
            description?.text = product.description
        }
    }
}