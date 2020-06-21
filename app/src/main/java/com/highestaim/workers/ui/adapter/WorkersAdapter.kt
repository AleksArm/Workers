package com.highestaim.workers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.workers.R
import com.highestaim.workers.model.WorkersModel.Data
import com.highestaim.workers.ui.adapter.WorkersAdapter.MyViewHolder
import com.highestaim.workers.util.onClick
import kotlinx.android.synthetic.main.worker_item_view.view.*

class WorkersAdapter : ListAdapter<Data, MyViewHolder>(
    WorkersDiffUtil()
) {
    var onItemClick: ((Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.worker_item_view, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.onClick {
            onItemClick?.invoke(getItem(position))
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data) {
            itemView.employeeNameTextView.text = data.name
        }
    }
}

private class WorkersDiffUtil : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}