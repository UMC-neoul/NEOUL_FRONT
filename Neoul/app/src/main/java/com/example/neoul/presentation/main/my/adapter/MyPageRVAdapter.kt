package com.example.neoul.presentation.main.my.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.databinding.ItemMypageMerchandiseBinding
import com.example.neoul.presentation.main.my.data.MyPageItem
import com.example.neoul.presentation.main.my.data.MyPageProduct
import kotlinx.coroutines.flow.merge

class MyPageRVAdapter (val mypageclickListener: (MyPageProduct)->Unit): RecyclerView.Adapter<MyPageRVAdapter.ViewHolder>() {

    private var myPageProduct : List<MyPageProduct> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMypageMerchandiseBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

    )


    override fun onBindViewHolder(holder: MyPageRVAdapter.ViewHolder, position: Int) {
        holder.bind(myPageProduct[position])
    }

    override fun getItemCount(): Int = myPageProduct.size

    fun setList(list:List<MyPageProduct>){
        myPageProduct = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding:ItemMypageMerchandiseBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        val imgdata = null

        fun bind(data: MyPageProduct){
            Glide.with(itemView)
                .load(if (data.productImgList.isNotEmpty()) data.productImgList[0] else R.drawable.base_img)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.itemMypageMerchandise)
            binding.price.text = data.price.toString()
            binding.name.text = data.productName
            binding.root.setOnClickListener {
                mypageclickListener(data)
            }
        }
    }
}