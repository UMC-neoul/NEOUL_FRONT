package com.example.neoul.presentation.main.my.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        fun bind(data: MyPageProduct){
            Glide.with(itemView)
                .load(data.productImgList)
                .into(binding.itemMypageMerchandise)
            //binding.itemMypageMerchandise.setImageResource(merchan.wareImageUrl!!)
            binding.price.text = data.price.toString()
            binding.name.text = data.brandName
            binding.sale.text = data.price.toString()
        }
    }
}