package com.example.neoul.presentation.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoul.data.model.CategoryItem
import com.example.neoul.data.model.Product
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.databinding.ItemCatItemBinding

class TabRVAdapter(
    private val itemList: List<Data>,
    val productClickListener: (Data) -> Unit,
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