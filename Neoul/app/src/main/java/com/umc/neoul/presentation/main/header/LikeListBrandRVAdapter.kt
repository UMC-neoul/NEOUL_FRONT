package com.umc.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.response.like.brand.LikedBrand
import com.umc.neoul.databinding.ItemHomeBrandBinding

class LikeListBrandRVAdapter(
    private val itemList: List<LikedBrand>,
    val brandClickListener: (LikedBrand) -> Unit
) :
    RecyclerView.Adapter<LikeListBrandRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemHomeBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: LikedBrand) {

            viewBinding.viewContent.setOnClickListener {
                brandClickListener(data)
            }
            viewBinding.apply {
                Glide.with(itemView)
                .load(data.brandImg)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(imgBrand)

                textBrandContent.text = data.brandIntro
                textBrandTitle.text = data.brandName
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding =
            ItemHomeBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}