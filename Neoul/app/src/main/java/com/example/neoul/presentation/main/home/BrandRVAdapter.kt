package com.example.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.adapter.ProductHorizontalRVAdapter
import com.example.neoul.data.model.Brand
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.Product
import com.example.neoul.databinding.ItemHomeBrandBinding

class BrandRVAdapter(
    val itemList: ArrayList<BrandItem>,
    val productClickListener: (Product) -> Unit
) : RecyclerView.Adapter<BrandRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemHomeBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: BrandItem,isFirstItem:Boolean) {
            viewBinding.apply {
                textBrandTitle.text = data.name
                textBrandContent.text = data.content

                recyclerBrandProduct.adapter =
                    ProductHorizontalRVAdapter(productClickListener, data.productList)

                if (isFirstItem){
                    recyclerBrandProduct.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandRVAdapter.DataViewHolder {
        val viewBinding =
            ItemHomeBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BrandRVAdapter.DataViewHolder, position: Int) {
        holder.bind(itemList[position],position==0)
    }

    override fun getItemCount(): Int = 3


}