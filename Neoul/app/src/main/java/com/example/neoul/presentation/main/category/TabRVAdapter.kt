package com.example.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.CategoryItem
import com.example.neoul.databinding.ItemCatItemBinding

class TabRVAdapter(private val itemList: ArrayList<CategoryItem>) :
    RecyclerView.Adapter<TabRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemCatItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: CategoryItem) {
            viewBinding.textName.text = data.brandName
            viewBinding.textTitle.text = data.title
            viewBinding.textPercent.text = data.percent.toString()
            viewBinding.textPrice.text = data.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding =
            ItemCatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}