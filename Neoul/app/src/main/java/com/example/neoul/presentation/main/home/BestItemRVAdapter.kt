package com.example.neoul.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.response.product.all.Data
import com.example.neoul.databinding.ItemBestBinding

class BestItemRVAdapter(
    val itemList: ArrayList<Data>,
    val productClickListener: (Data) -> Unit,
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