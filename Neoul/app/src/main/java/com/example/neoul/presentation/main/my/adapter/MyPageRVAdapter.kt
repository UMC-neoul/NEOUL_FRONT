package com.example.neoul.presentation.main.my.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.databinding.FragmentMypageBinding
import com.example.neoul.presentation.main.my.data.Merchan
import com.example.neoul.databinding.ItemMypageMerchandiseBinding
import kotlinx.coroutines.flow.merge

class MyPageRVAdapter (): RecyclerView.Adapter<MyPageRVAdapter.ViewHolder>() {

    private val merchan = ArrayList<Merchan>()



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyPageRVAdapter.ViewHolder {
        val binding: ItemMypageMerchandiseBinding = ItemMypageMerchandiseBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return ViewHolder(binding, viewGroup.context)
    }

    override fun onBindViewHolder(holder: MyPageRVAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = merchan.size

    @SuppressLint("NotifyDataSetChanged")
    fun addMerchan(merchan: ArrayList<Merchan>){
        this.merchan.clear()
        this.merchan.addAll(merchan)

        notifyDataSetChanged()
    }

    fun removemerchan(position: Int){
        merchan.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding:ItemMypageMerchandiseBinding,val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(merchan: Merchan){
            Glide.with(context)
                .load(merchan.wareImageUrl)
                .into(binding.itemMypageMerchandise)
            //binding.itemMypageMerchandise.setImageResource(merchan.wareImageUrl!!)
            binding.price.text = merchan.price.toString()
            binding.name.text = merchan.ware
            binding.sale.text = merchan.salePercent
        }
    }
}