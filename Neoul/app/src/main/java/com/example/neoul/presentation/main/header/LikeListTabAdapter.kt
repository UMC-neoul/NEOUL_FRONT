package com.example.neoul.presentation.main.header

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LikeListTabAdapter(fragmentActivity: LikeListActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabLikeProductFragment()
            1 -> TabLikeBrandFragment()
            else -> TabLikeProductFragment()
        }
    }


}