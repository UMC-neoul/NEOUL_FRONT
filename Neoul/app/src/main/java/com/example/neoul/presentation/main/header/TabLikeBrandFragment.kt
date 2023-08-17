package com.example.neoul.presentation.main.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.data.response.like.brand.LikedBrand
import com.example.neoul.data.response.like.product.LikedProduct
import com.example.neoul.databinding.FragmentTabClothesBinding
import com.example.neoul.databinding.FragmentTabLikeBrandBinding
import com.example.neoul.databinding.FragmentTabLikeProductBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.CategoryViewModel
import com.example.neoul.presentation.main.category.LikeListBrandRVAdapter
import com.example.neoul.presentation.main.category.LikeListProductRVAdater
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TabLikeBrandFragment : BaseFragment<LikeListViewModel, FragmentTabLikeBrandBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding() = FragmentTabLikeBrandBinding.inflate(layoutInflater)

    override fun observeDate() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLikeBrandBinding.inflate(layoutInflater)
        productAdapter(getDummyItemList())
        return binding.root
    }

    private fun productAdapter(itemList: List<LikedBrand>) {
        val dataRVAdapter = LikeListBrandRVAdapter(itemList)

        binding.recyclerBrand.adapter = dataRVAdapter
        binding.recyclerBrand.layoutManager =
            LinearLayoutManager(context)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerBrand.setHasFixedSize(true)
    }

    private fun getDummyItemList(): ArrayList<LikedBrand> {
        val dummyList = ArrayList<LikedBrand>().apply {
            add(LikedBrand(1, "바다마을 목걸이"))
            add(LikedBrand(2, "바다마을 목걸이3"))
            add(LikedBrand(3, "바다마을 목걸이"))
            add(LikedBrand(4, "바다마을 목걸이3"))
            add(LikedBrand(5, "바다마을 목걸이"))


        }
        return dummyList
    }
}