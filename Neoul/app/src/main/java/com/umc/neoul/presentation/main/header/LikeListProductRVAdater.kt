package com.umc.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.response.like.product.LikedProduct
import com.umc.neoul.databinding.ItemCatItemBinding
import com.umc.neoul.presentation.main.header.LikeListViewModel


class LikeListProductRVAdater(
    private val itemList: List<LikedProduct>,
    val productClickListener: (LikedProduct) -> Unit,
    val viewModel: LikeListViewModel
) :
    RecyclerView.Adapter<LikeListProductRVAdater.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemCatItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: LikedProduct) {

            viewBinding.imgItem.setOnClickListener {
                productClickListener(data)
            }

            viewBinding.apply {

                imgHeart.setImageResource(if (data.liked) R.drawable.favorite_18 else R.drawable.favorite_border)

                imgHeart.setOnClickListener {
                    val currentPosition = adapterPosition
                    val currentHeartStatus = itemList[currentPosition].liked

                    itemList[currentPosition].liked = !currentHeartStatus

                    imgHeart.setImageResource(if (currentHeartStatus) R.drawable.favorite_border else R.drawable.favorite_18)
                    viewModel.clickLikeBtn(data.productId, currentHeartStatus)
                }

                if (data.discountedSalePrice.toString() == "0") {
                    textPrice.text = data.price.toString()
                } else {
                    textPrice.text = data.discountedSalePrice.toString()
                }
                textTitle.text = data.productName
                textName.text = data.brandName
                textPercent.text = data.discountedRatio.toString()

                Glide.with(itemView)
                    .load(data.productImgList.firstOrNull())
                    .error(R.drawable.base)
                    .fallback(R.drawable.base)
                    .into(imgItem)


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