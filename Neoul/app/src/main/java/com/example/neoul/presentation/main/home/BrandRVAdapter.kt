package com.example.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.Brand
import com.example.neoul.databinding.ItemBrandBinding

class BrandRVAdapter(val itemList: ArrayList<Brand>):RecyclerView.Adapter<BrandRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Brand) {
            viewBinding.apply {
                textBrandTitle.text = data.brandName
                textBrandContent.text = data.brandContent

            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandRVAdapter.DataViewHolder {
        val viewBinding =
            ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BrandRVAdapter.DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

}