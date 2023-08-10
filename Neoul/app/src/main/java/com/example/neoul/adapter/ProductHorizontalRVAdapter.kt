package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.Product
import com.example.neoul.databinding.ItemProductHorizontalBinding

class ProductHorizontalRVAdapter(
    var productClickListener: (Product) -> Unit,
    var productList: List<Product> = listOf()
) : RecyclerView.Adapter<ProductHorizontalRVAdapter.ProductHorizontalHolder>() {


    inner class ProductHorizontalHolder(
        private val binding: ItemProductHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Product) {
            binding.root.setOnClickListener {
                productClickListener(data)
            }
            binding.productName.text =data.name
            binding.productPrice.text = data.price.toString()
            //binding.productSalePercent.text = data.percent.toString()+"%"
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

    fun setList(list: List<Product>) {
        productList = list
        notifyDataSetChanged()
    }
}