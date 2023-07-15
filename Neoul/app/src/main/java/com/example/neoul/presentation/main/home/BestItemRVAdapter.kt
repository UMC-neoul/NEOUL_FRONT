package com.example.neoul.presentation.main.home

import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.ItemBestBinding

class BestItemRVAdapter(val itemList: ArrayList<GoodsItem>):RecyclerView.Adapter<BestItemRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemBestBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: GoodsItem){
            viewBinding.apply {
                textTitle.text = data.title
                textPercent.text = data.percent.toString()
                textPrice.text = data.price.toString()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemBestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int =itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}