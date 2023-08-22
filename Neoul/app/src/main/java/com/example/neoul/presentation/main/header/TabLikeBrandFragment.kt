package com.example.neoul.presentation.main.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.data.response.like.brand.LikedBrand
import com.example.neoul.data.response.like.brand.likedBrandToBrandItem
import com.example.neoul.databinding.FragmentTabLikeBrandBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.main.category.LikeListBrandRVAdapter
import com.example.neoul.presentation.main.category.LikeListProductRVAdater
import com.example.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel


class TabLikeBrandFragment : BaseFragment<LikeListViewModel, FragmentTabLikeBrandBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding() = FragmentTabLikeBrandBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.likedBrandLiveData.observe(viewLifecycleOwner) {
        productAdapter(it.likedBrands)
        binding.textBrandCnt.text = "브랜드 "+it.brandCnt
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLikeBrandBinding.inflate(layoutInflater)

        return binding.root
    }

    private fun productAdapter(itemList: List<LikedBrand>) {
        val dataRVAdapter = LikeListBrandRVAdapter(itemList) { data ->
            val brand = likedBrandToBrandItem(data)
            startActivity(
                BrandDetailActivity.newIntent(requireContext(), brand)
            )
        }

        binding.recyclerBrand.adapter = dataRVAdapter
        binding.recyclerBrand.layoutManager =
            LinearLayoutManager(context)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerBrand.setHasFixedSize(true)
    }


}