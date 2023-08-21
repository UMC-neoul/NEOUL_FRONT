package com.example.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.data.response.like.brand.LikedBrand
import com.example.neoul.databinding.ItemHomeBrandBinding

class LikeListBrandRVAdapter(
    private val itemList: List<LikedBrand>,
    val brandClickListener: (LikedBrand) -> Unit
) :
    RecyclerView.Adapter<LikeListBrandRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemHomeBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: LikedBrand) {
//            viewBinding.textName.text = data.brandName
            viewBinding.textBrandTitle.text = data.brandName
            viewBinding.viewContent.setOnClickListener {
                brandClickListener(data)
            }

//            Glide.with(itemView)
//                .load(data.productImgList[0])
//                .error(R.drawable.base_img)
//                .fallback(R.drawable.base_img)
//                .into()

//            viewBinding.textPrice.text = data.price.toString()
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