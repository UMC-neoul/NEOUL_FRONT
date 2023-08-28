package com.umc.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.model.BrandItem
import com.umc.neoul.databinding.ItemHomeBrandBinding

class BrandCardRVAdapter(val brandClickListener: (BrandItem) -> Unit) :
    RecyclerView.Adapter<BrandCardRVAdapter.BrandItemHolder>() {

    private var brandList: List<BrandItem> = listOf()

    inner class BrandItemHolder(
        private val binding: ItemHomeBrandBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BrandItem) {
            binding.root.setOnClickListener {
                brandClickListener(data)
            }
            binding.textBrandContent.text = data.content
            binding.textBrandTitle.text = data.name
            Glide.with(itemView)
                .load(data.image)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.imgBrand)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandItemHolder =
        BrandItemHolder(
            ItemHomeBrandBinding.inflate(
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