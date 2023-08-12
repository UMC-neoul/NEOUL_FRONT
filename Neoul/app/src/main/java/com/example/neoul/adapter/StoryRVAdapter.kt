package com.example.neoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neoul.R
import com.example.neoul.data.model.Story
import com.example.neoul.databinding.ItemStoryBinding

class StoryRVAdapter(
    var storyClickListener: (Story) ->Unit) : RecyclerView.Adapter<StoryRVAdapter.StoryHolder>() {

    var storyList: List<Story> = listOf()

    inner class StoryHolder(
        private val binding: ItemStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Story) = with(binding){
            storyTitle.text = data.title
            binding.root.setOnClickListener{
                storyClickListener(data)
            }
            Glide.with(itemView)
                .load(data.image)
                .error(R.drawable.base_img)
                .fallback(R.drawable.base_img)
                .into(binding.storyImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder =
        StoryHolder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = storyList.size

    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
        holder.bind(storyList[position])
    }

    fun setList(list :List<Story>){
        storyList = list
        notifyDataSetChanged()
    }

}