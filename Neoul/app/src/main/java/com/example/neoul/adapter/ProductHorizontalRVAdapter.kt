package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.ItemProductHorizontalBinding

class ProductHorizontalRVAdapter(
    var productClickListener: (GoodsItem) -> Unit,
    var productList: List<GoodsItem> = listOf()
) : RecyclerView.Adapter<ProductHorizontalRVAdapter.ProductHorizontalHolder>() {


    inner class ProductHorizontalHolder(
        private val binding: ItemProductHorizontalBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHorizontalHolder =
        ProductHorizontalHolder(
            ItemProductHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductHorizontalHolder, position: Int) {
        holder.bind(productList[position])
    }


    override fun getItemCount() = productList.size

    fun setList(list: List<GoodsItem>) {
        productList = list
        notifyDataSetChanged()
    }
}