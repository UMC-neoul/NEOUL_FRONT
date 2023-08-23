package com.example.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.response.product.category.Data
//import com.example.neoul.data.response.product.all.Data
import com.example.neoul.databinding.ItemBestBinding

class BestItemRVAdapter(
    val itemList: ArrayList<Data>,
    val productClickListener: (Data) -> Unit,
    val viewModel: HomeViewModel
) :
    RecyclerView.Adapter<BestItemRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemBestBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Data) {
            viewBinding.apply {
                imgItem.setOnClickListener {
                    productClickListener(data)
                }
                textTitle.text = data.productName
                textPercent.text = data.discountedRatio.toString()
                textPrice.text = data.price.toString()
                Glide.with(itemView)
                    .load(data.productImgList[0])
                    .error(R.drawable.base_img)
                    .fallback(R.drawable.base_img)
                    .into(imgItem)
                if (data.liked) {
                    imgHeart.setImageResource(R.drawable.favorite_18)

                    var i = 0
                    imgHeart.setOnClickListener {
                        if (i % 2 == 0) {
                            imgHeart.setImageResource(R.drawable.favorite_border)
                            viewModel.clickLikeBtn(data.productId, true)
                        } else {
                            imgHeart.setImageResource(R.drawable.favorite_18)
                            viewModel.clickLikeBtn(data.productId, false)
                        }
                        i++


                    }
                } else {
                    imgHeart.setImageResource(R.drawable.favorite_border)

                    var i = 0
                    imgHeart.setOnClickListener {
                        if (i % 2 == 0) {
                            imgHeart.setImageResource(R.drawable.favorite_18)
                            viewModel.clickLikeBtn(data.productId, false)
                        } else {
                            imgHeart.setImageResource(R.drawable.favorite_border)
                            viewModel.clickLikeBtn(data.productId, true)
                        }
                        i++


                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding =
            ItemBestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = if (itemList.size > 4) 4 else itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}