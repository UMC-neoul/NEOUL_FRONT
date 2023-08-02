package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.ItemBrandBinding

class BrandItemRVAdapter(val brandClickListener: (BrandItem) -> Unit, val productClickListener: (GoodsItem) -> Unit) :
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