package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.Product
import com.example.neoul.databinding.ItemProductGridBinding

class ProductGridRVAdapter(
    var productClickListener: (Product) -> Unit,
    var productList: List<Product> = listOf()
) : RecyclerView.Adapter<ProductGridRVAdapter.ProductGridHolder>() {


    inner class ProductGridHolder(
        private val binding: ItemProductGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Product) {
            binding.root.setOnClickListener {
                productClickListener(data)
            }
            binding.productName.text =data.name
            binding.productPrice.text = data.price.toString()
            //binding.productSalePercent.text = data.price.toString()+"%"
        }
    }


    override fun onBindViewHolder(holder: ProductGridRVAdapter.ProductGridHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridHolder =
        ProductGridHolder(ItemProductGridBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount() = productList.size

    fun setList(list: List<Product>) {
        productList = list
        notifyDataSetChanged()
    }
}