package edu.miu.cvBuilder.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MainView(itemView: View?) : RecyclerView.ViewHolder(
    itemView!!
) {
    var currentPosition = 0
    open fun onBind(position: Int) {
        currentPosition = position
    }
}