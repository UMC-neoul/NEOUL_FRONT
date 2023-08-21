package com.example.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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


//            if(data.likes){
//                imgHeart.setImageResource(R.drawable.favorite_18)
//
//                var i=0
//                imgHeart.setOnClickListener {
//                    if(i%2==0){
//                        imgHeart.setImageResource(R.drawable.favorite_border)
//                    }
//                    else{
//                        imgHeart.setImageResource(R.drawable.favorite_18)
//                    }
//                    i++
////                       api                      추가
//                    viewModel.clickLikeBtn(data.productId)
//                }
//            }
//            else{
                var i = 0
                imgHeart.setOnClickListener {
                    if (i % 2 == 0) {
                        imgHeart.setImageResource(R.drawable.favorite_18)
                    } else {
                        imgHeart.setImageResource(R.drawable.favorite_border)
                    }
                    i++
                    viewModel.clickLikeBtn(data.productId)
                }
//            }
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