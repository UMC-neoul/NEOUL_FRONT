package com.example.neoul.presentation.main.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.data.response.like.product.LikedProduct
import com.example.neoul.databinding.FragmentTabLikeProductBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.LikeListProductRVAdater
import org.koin.android.viewmodel.ext.android.viewModel

class TabLikeProductFragment : BaseFragment<LikeListViewModel, FragmentTabLikeProductBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding() = FragmentTabLikeProductBinding.inflate(layoutInflater)

    override fun observeDate() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLikeProductBinding.inflate(layoutInflater)
        productAdapter(getDummyItemList())
        return binding.root
    }

    private fun productAdapter(itemList: List<LikedProduct>) {
        val dataRVAdapter = LikeListProductRVAdater(itemList)

        binding.recyclerItem.adapter = dataRVAdapter
        binding.recyclerItem.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerItem.setHasFixedSize(true)
    }

    private fun getDummyItemList(): ArrayList<LikedProduct> {
        val dummyList = ArrayList<LikedProduct>().apply {
            add(LikedProduct(1, "바다마을 목걸이"))
            add(LikedProduct(2, "바다마을 목걸이3"))
            add(LikedProduct(3, "바다마을 목걸이"))
            add(LikedProduct(4, "바다마을 목걸이3"))
            add(LikedProduct(5, "바다마을 목걸이"))


        }
        return dummyList
    }
}