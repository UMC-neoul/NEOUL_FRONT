package com.example.neoul.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.data.model.Brand
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.FragmentHomeBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

    private lateinit var viewBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // bestAdapter() 메서드 호출
        bestAdapter(getDummyItemList()) // 이곳에 적절한 데이터 리스트를 전달해야 합니다.
        recommandAdapter(getDummyItemList2())
        brandAdapter(getDummyItemList3())
    }

    private fun bestAdapter(itemList: ArrayList<GoodsItem>) {
        val dataRVAdapter = BestItemRVAdapter(itemList)

        viewBinding.recyclerBest.adapter = dataRVAdapter
        viewBinding.recyclerBest.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
        dataRVAdapter.notifyDataSetChanged()
        viewBinding.recyclerBest.setHasFixedSize(true)
    }

    private fun recommandAdapter(itemList: ArrayList<GoodsItem>) {
        val dataRVAdapter = BestItemRVAdapter(itemList)

        viewBinding.recyclerRecommend.adapter = dataRVAdapter
        viewBinding.recyclerRecommend.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
        dataRVAdapter.notifyDataSetChanged()
        viewBinding.recyclerRecommend.setHasFixedSize(true)
    }

    private fun brandAdapter(itemList: ArrayList<Brand>) {
        val dataRVAdapter = BrandRVAdapter(itemList)

        viewBinding.recyclerBrand.adapter = dataRVAdapter
        viewBinding.recyclerBrand.layoutManager =
            LinearLayoutManager(context)
        dataRVAdapter.notifyDataSetChanged()
        viewBinding.recyclerBrand.setHasFixedSize(true)
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