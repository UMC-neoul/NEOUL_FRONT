package com.example.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.R
import com.example.neoul.data.response.like.product.LikedProduct
import com.example.neoul.databinding.ItemCatItemBinding
import com.example.neoul.presentation.main.header.LikeListViewModel

class LikeListProductRVAdater(private val itemList: List<LikedProduct>, val productClickListener: (LikedProduct) -> Unit,val viewModel: LikeListViewModel) :
    RecyclerView.Adapter<LikeListProductRVAdater.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemCatItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: LikedProduct) {
//            viewBinding.textName.text = data.brandName
            viewBinding.textTitle.text = data.productName
            viewBinding.imgItem.setOnClickListener {
                productClickListener(data)
            }
//            viewBinding.textPrice.text = data.price.toString()

            viewBinding.apply {
                imgHeart.setImageResource(R.drawable.favorite_18)
                var i = 0
                imgHeart.setOnClickListener {
                    if (i % 2 == 0) {
                        imgHeart.setImageResource(R.drawable.favorite_border)
                    } else {
                        imgHeart.setImageResource(R.drawable.favorite_18)
                    }
                    i++
                    viewModel.clickLikeBtn(data.productId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding =
            ItemCatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}