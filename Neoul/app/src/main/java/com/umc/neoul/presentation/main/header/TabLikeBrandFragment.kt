package com.umc.neoul.presentation.main.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.neoul.data.response.like.brand.LikedBrand
import com.umc.neoul.data.response.like.brand.likedBrandToBrandItem
import com.umc.neoul.databinding.FragmentTabLikeBrandBinding
import com.umc.neoul.presentation.BaseFragment
import com.umc.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.umc.neoul.presentation.main.category.LikeListBrandRVAdapter
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