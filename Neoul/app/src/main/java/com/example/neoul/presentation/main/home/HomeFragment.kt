package com.example.neoul.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.R
import com.example.neoul.data.model.Brand
import com.example.neoul.data.model.BrandDetail
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.response.brand.list.BrandResponse
import com.example.neoul.data.response.product.all.Data
import com.example.neoul.data.response.product.all.dataToProduct
import com.example.neoul.databinding.FragmentHomeBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.main.category.TabRVAdapter
import com.example.neoul.presentation.main.header.LikeListActivity
import com.example.neoul.presentation.main.header.SearchActivity
import com.example.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    var data: BrandResponse? = null
    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.brandLiveData.observe(viewLifecycleOwner) {
        brandAdapter(it as ArrayList<BrandItem>)
        observeDate2()
    }

    private fun observeDate2() = viewModel.allLiveData.observe(viewLifecycleOwner) {
        bestAdapter(it as ArrayList<Data>)
        recommandAdapter(it)
    }

    companion object {
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imgBoard.setOnClickListener {
            startActivity(Intent(requireContext(), EventActivity::class.java))
        }
        binding.imgSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))

        }


        binding.imgHeart.setOnClickListener {
            startActivity(Intent(requireContext(), LikeListActivity::class.java))

        }

//        bestAdapter(getDummyItemList())
//        recommandAdapter(getDummyItemList2())

    }

    private fun bestAdapter(itemList: ArrayList<Data>) {
        val dataRVAdapter = BestItemRVAdapter(itemList,{ data ->
            val product = dataToProduct(data)
            startActivity(
                ProductActivity.newIntent(requireContext(), product)
            )

        },viewModel)

        binding.recyclerBest.adapter = dataRVAdapter
        binding.recyclerBest.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerBest.setHasFixedSize(true)
    }

    private fun recommandAdapter(itemList: ArrayList<Data>) {
        val dataRVAdapter = BestItemRVAdapter(itemList, { data ->
            val product = dataToProduct(data)
            startActivity(
                ProductActivity.newIntent(requireContext(), product)
            )
        }, viewModel)

        binding.recyclerRecommend.adapter = dataRVAdapter
        binding.recyclerRecommend.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerRecommend.setHasFixedSize(true)
    }

    private fun brandAdapter(itemList: ArrayList<BrandItem>) {
        val dataRVAdapter = BrandRVAdapter(
            itemList,
            { product ->
                startActivity(ProductActivity.newIntent(requireContext(), product))
            },
            { brandItem ->
                startActivity(BrandDetailActivity.newIntent(requireContext(), brandItem))
            }
        )

        binding.recyclerBrand.adapter = dataRVAdapter
        binding.recyclerBrand.layoutManager =
            LinearLayoutManager(context)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerBrand.setHasFixedSize(true)

    }

    private fun getDummyItemList(): ArrayList<GoodsItem> {
        val dummyList = ArrayList<GoodsItem>().apply {
            add(GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"))
            add(GoodsItem("[핸드메이드] ", 11, 21300, "몰라"))
            add(GoodsItem("[핸드메이드] 푸른마음 귀걸이", 15, 50000, "몰라"))
            add(GoodsItem("[핸드메이드] 푸른마음 귀걸이", 45, 60000, "몰라"))
        }
        return dummyList
    }

    private fun getDummyItemList2(): ArrayList<GoodsItem> {
        val dummyList = ArrayList<GoodsItem>().apply {
            add(GoodsItem("바다마을 목걸이", 5, 16400, "몰라"))
            add(GoodsItem("[핸드메이드] ", 11, 21300, "몰라"))
            add(GoodsItem("[핸드메이드] 푸른마음 귀걸이", 15, 50000, "몰라"))
            add(GoodsItem("[핸드메이드] 푸른마음 귀걸이", 45, 60000, "몰라"))
        }
        return dummyList
    }

    private fun getDummyItemList3(): ArrayList<Brand> {
        val dummyList = ArrayList<Brand>().apply {
            add(Brand("브랜드명1", "한줄소개 1", ""))
            add(Brand("브랜드명1", "한줄소개 1", ""))
            add(Brand("브랜드명1", "한줄소개 1", ""))

        }
        return dummyList
    }


}