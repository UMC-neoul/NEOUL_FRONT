package com.umc.neoul.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.model.Product
import com.umc.neoul.databinding.ItemProductGridBinding
import java.text.DecimalFormat

class ProductGridRVAdapter(
    var productClickListener: (Product) -> Unit,
    var productList: List<Product> = listOf()
) : RecyclerView.Adapter<ProductGridRVAdapter.ProductGridHolder>() {

    var brandName =""
    inner class ProductGridHolder(
        private val binding: ItemProductGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Product) {
            binding.root.setOnClickListener {
                productClickListener(data)
            }
            binding.brandName.text = brandName
            binding.productName.text =data.name
            binding.productPrice.text = DecimalFormat("#,###").format(data.price)+"원"
            //binding.productSalePercent.text = data.price.toString()+"%"
            Glide.with(itemView)
                .load(data.productImg)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.productImage)
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