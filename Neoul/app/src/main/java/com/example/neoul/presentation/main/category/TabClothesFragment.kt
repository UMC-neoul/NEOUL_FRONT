package com.example.neoul.presentation.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.neoul.data.model.CategoryItem

import com.example.neoul.databinding.FragmentTabClothesBinding

class TabClothesFragment : Fragment() {
    private lateinit var viewBinding: FragmentTabClothesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTabClothesBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter(getDummyItemList())
    }

    private fun categoryAdapter(itemList: ArrayList<CategoryItem>) {
        val dataRVAdapter = TabRVAdapter(itemList)

        viewBinding.recyclerItem.adapter = dataRVAdapter
        viewBinding.recyclerItem.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        dataRVAdapter.notifyDataSetChanged()
        viewBinding.recyclerItem.setHasFixedSize(true)
    }

    private fun getDummyItemList(): ArrayList<CategoryItem> {
        val dummyList = ArrayList<CategoryItem>().apply {
            add(CategoryItem("브랜드1", "바다마을 목걸이", 11, 25100, ""))
            add(CategoryItem("브랜드2", "바다마을 목걸이3", 11, 20100, ""))
            add(CategoryItem("브랜드3", "바다마을 목걸이", 11, 23100, ""))
            add(CategoryItem("브랜드4", "바다마을 목걸이3", 16, 60100, ""))
            add(CategoryItem("브랜드5", "바다마을 목걸이", 21, 27100, ""))


        }
        return dummyList
    }

}