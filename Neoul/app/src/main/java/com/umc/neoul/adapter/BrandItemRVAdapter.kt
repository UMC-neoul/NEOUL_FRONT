package com.umc.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.model.BrandItem
import com.umc.neoul.data.model.Product
import com.umc.neoul.databinding.ItemBrandBinding

class BrandItemRVAdapter(val brandClickListener: (BrandItem) -> Unit, val productClickListener: (Product) -> Unit) :
    RecyclerView.Adapter<BrandItemRVAdapter.BrandItemHolder>() {

    private var brandList: List<BrandItem> = listOf()

    inner class BrandItemHolder(
        private val binding: ItemBrandBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BrandItem) {
            binding.recyclerVIew.apply {
                adapter = ProductHorizontalRVAdapter(productClickListener, data.productList)
            }
            binding.brandName.text = data.name
            binding.brandContent.text = data.content
            binding.brandCardView.setOnClickListener {
                brandClickListener(data)
            }
            Glide.with(itemView)
                .load(data.image)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.brandLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandItemHolder =
        BrandItemHolder(
            ItemBrandBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = brandList.size

    override fun onBindViewHolder(holder: BrandItemHolder, position: Int) {
        holder.bind(brandList[position])
    }

    fun setList(list: List<BrandItem>){
        brandList = list
        notifyDataSetChanged()
    }
}