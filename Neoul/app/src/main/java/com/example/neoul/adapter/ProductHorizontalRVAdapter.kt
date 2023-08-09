package com.example.neoul.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.data.model.Product
import com.example.neoul.databinding.ItemProductHorizontalBinding
import java.text.DecimalFormat

class ProductHorizontalRVAdapter(
    var productClickListener: (Product) -> Unit,
    var productList: List<Product> = listOf()
) : RecyclerView.Adapter<ProductHorizontalRVAdapter.ProductHorizontalHolder>() {


    inner class ProductHorizontalHolder(
        private val binding: ItemProductHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Product) {
            binding.root.setOnClickListener {
                productClickListener(data)
            }
            binding.productName.text =data.name
            binding.productPrice.text = DecimalFormat("#,###").format(data.price)+"원"
            //binding.productSalePercent.text = data.percent.toString()+"%"
            Glide.with(itemView)
                .load(data.productUrl)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.productImage)
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