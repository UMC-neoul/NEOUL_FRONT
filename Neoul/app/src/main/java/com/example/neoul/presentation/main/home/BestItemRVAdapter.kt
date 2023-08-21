package com.example.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.R
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.response.product.all.Data
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
//                textPercent.text = data.
                textPrice.text = data.price.toString()
                if(data.phearted){
                    imgHeart.setImageResource(R.drawable.favorite_18)

                    var i=0
                    imgHeart.setOnClickListener {
                        if(i%2==0){
                            imgHeart.setImageResource(R.drawable.favorite_border)
                        }
                        else{
                            imgHeart.setImageResource(R.drawable.favorite_18)
                        }
                        i++
//                       api                      추가
                        viewModel.clickLikeBtn(data.productId)
                    }
                }
                else{
                    var i=0
                    imgHeart.setOnClickListener {
                        if(i%2==0){
                            imgHeart.setImageResource(R.drawable.favorite_18)
                        }
                        else{
                            imgHeart.setImageResource(R.drawable.favorite_border)
                        }
                        i++
                        viewModel.clickLikeBtn(data.productId)
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