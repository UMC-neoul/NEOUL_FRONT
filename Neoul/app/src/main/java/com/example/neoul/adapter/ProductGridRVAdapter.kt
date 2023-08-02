package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.ItemProductGridBinding

class ProductGridRVAdapter(
    var productClickListener: (GoodsItem) -> Unit,
    var productList: List<GoodsItem> = listOf()
) : RecyclerView.Adapter<ProductGridRVAdapter.ProductGridHolder>() {


    inner class ProductGridHolder(
        private val binding: ItemProductGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GoodsItem) {
            binding.root.setOnClickListener {
                productClickListener(data)
            }
            binding.productName.text =data.title
            binding.productPrice.text = data.price.toString()
            binding.productSalePercent.text = data.percent.toString()+"%"
        }
    }


    override fun onBindViewHolder(holder: ProductGridRVAdapter.ProductGridHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridHolder =
        ProductGridHolder(ItemProductGridBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount() = productList.size

    fun setList(list: List<GoodsItem>) {
        productList = list
        notifyDataSetChanged()
    }
}