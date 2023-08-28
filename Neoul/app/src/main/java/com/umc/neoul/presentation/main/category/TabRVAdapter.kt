package com.umc.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.data.response.product.category.Data
import com.umc.neoul.databinding.ItemCatItemBinding

class TabRVAdapter(
    private val itemList: List<Data>,
    val productClickListener: (Data) -> Unit,
    val viewModel: CategoryViewModel
) :
    RecyclerView.Adapter<TabRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemCatItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Data) {


            viewBinding.imgItem.setOnClickListener {
                productClickListener(data)
            }

            viewBinding.textName.text = data.brandName
            viewBinding.textTitle.text = data.productName

            viewBinding.textPrice.text = data.price.toString()
            viewBinding.apply {


                Glide.with(itemView)
                    .load(data.productImgList[0])
                    .error(R.drawable.base_img)
                    .fallback(R.drawable.base_img)
                    .into(imgItem)
                textPercent.text = data.discountedRatio.toString()

                imgHeart.setImageResource(if (data.liked) R.drawable.favorite_18 else R.drawable.favorite_border)

                imgHeart.setOnClickListener {
                    val currentPosition = adapterPosition
                    val currentHeartStatus = itemList[currentPosition].liked

                    itemList[currentPosition].liked = !currentHeartStatus

                    imgHeart.setImageResource(if (currentHeartStatus) R.drawable.favorite_border else R.drawable.favorite_18)
                    viewModel.clickLikeBtn(data.productId, currentHeartStatus)
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