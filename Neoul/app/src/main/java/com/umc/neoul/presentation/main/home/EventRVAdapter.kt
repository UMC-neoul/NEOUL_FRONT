package com.umc.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.neoul.data.model.Brand
import com.umc.neoul.data.model.Event
import com.umc.neoul.databinding.ItemEventBinding


class EventRVAdapter(val itemList: ArrayList<Event>):RecyclerView.Adapter<EventRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemEventBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Event) {
            viewBinding.apply {
                textTitle.text = data.title
                textSubTitle.text = data.subTitle

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
       val viewBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int =itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}