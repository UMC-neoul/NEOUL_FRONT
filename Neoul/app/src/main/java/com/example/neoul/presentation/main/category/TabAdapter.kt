package com.example.neoul.presentation.main.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragmentActivity: CategoryFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabClothesFragment()
            1 -> TabPropFragment()
            2 -> TabAccFragment()
            3 -> TabStuffFragment()
            4 -> TabEtcFragment()
            else -> TabClothesFragment()
        }
    }
}