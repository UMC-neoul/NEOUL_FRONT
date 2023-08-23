package com.example.neoul.presentation.main.category

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.data.model.CategoryItem
import com.example.neoul.data.model.Product
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.databinding.ItemCatItemBinding

class TabRVAdapter(
    private val itemList: List<Data>,
    val productClickListener: (Data) -> Unit,
    val viewModel: CategoryViewModel
) :
    RecyclerView.Adapter<TabRVAdapter.DataViewHolder>() {
    private val heartStatus = SparseBooleanArray()

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
//                if (data.liked) {
//                    imgHeart.setImageResource(R.drawable.favorite_18)
//
//                    var i = 0
//                    imgHeart.setOnClickListener {
//                        if (i % 2 == 0) {
//                            imgHeart.setImageResource(R.drawable.favorite_border)
//                            viewModel.clickLikeBtn(data.productId, true)
//                        } else {
//                            imgHeart.setImageResource(R.drawable.favorite_18)
//                            viewModel.clickLikeBtn(data.productId, false)
//                        }
//                        i++
//
//
//                    }
//                } else {
//                    imgHeart.setImageResource(R.drawable.favorite_border)
//
//                    var i = 0
//                    imgHeart.setOnClickListener {
//                        if (i % 2 == 0) {
//                            imgHeart.setImageResource(R.drawable.favorite_18)
//                            viewModel.clickLikeBtn(data.productId, false)
//                        } else {
//                            imgHeart.setImageResource(R.drawable.favorite_border)
//                            viewModel.clickLikeBtn(data.productId, true)
//                        }
//                        i++
//
//
//                    }
//                }
                imgHeart.setImageResource(if (data.liked) R.drawable.favorite_18 else R.drawable.favorite_border)

                imgHeart.setOnClickListener {
                    // 클릭 이벤트 처리
                    val currentPosition = adapterPosition
                    val currentHeartStatus = itemList[currentPosition].liked // 현재 아이템의 liked 상태 가져오기

                    // 클릭 시 하트 상태 토글
                    itemList[currentPosition].liked = !currentHeartStatus

                    // 아이템의 하트 상태를 업데이트하고 ViewModel에 전달
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