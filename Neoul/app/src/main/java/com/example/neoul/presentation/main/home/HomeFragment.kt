package com.example.neoul.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.R
import com.example.neoul.data.model.Brand
import com.example.neoul.data.model.BrandDetail
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.response.brand.list.BrandResponse
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.data.response.product.category.dataToProduct
//import com.example.neoul.data.response.product.all.Data
//import com.example.neoul.data.response.product.all.dataToProduct
import com.example.neoul.databinding.FragmentHomeBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.main.MainMenuId
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.main.category.CategoryMenu
import com.example.neoul.presentation.main.category.TabRVAdapter
import com.example.neoul.presentation.main.header.LikeListActivity
import com.example.neoul.presentation.main.header.SearchActivity
import com.example.neoul.presentation.product.ProductActivity
import com.example.neoul.util.CategoryMenuBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    var data: BrandResponse? = null
    override val viewModel by viewModel<HomeViewModel>()

    private val categoryMenuBus by inject<CategoryMenuBus>()
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.brandLiveData.observe(viewLifecycleOwner) {
        brandAdapter(it as ArrayList<BrandItem>)
        best()
        recommend()
        nameDate()
    }

    private fun best() = viewModel.bestLiveData.observe(viewLifecycleOwner) {
        bestAdapter(it as ArrayList<Data>)

    }

    private fun recommend() = viewModel.recommendLiveData.observe(viewLifecycleOwner) {
        recommandAdapter(it as ArrayList<Data>)
    }

    private fun nameDate() = viewModel.nameLiveData.observe(viewLifecycleOwner) {
        binding.textName.text = it.name
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

    override fun initState() {
        super.initState()
        //브랜드 이동
        binding.viewBrand.setOnClickListener {
            (requireActivity() as MainActivity).goToTab(MainMenuId.BRAND)
        }
        //스토리 이동
        binding.viewStory.setOnClickListener {
            (requireActivity() as MainActivity).goToTab(MainMenuId.STORY)
        }
        //의류 이동
        binding.imgShirt.setOnClickListener {
            lifecycleScope.launch {
                categoryMenuBus.changeMenu(CategoryMenu.CLOTHES)
            }
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        //악세사리 이동
        binding.imgAccesary.setOnClickListener {
            lifecycleScope.launch {
                categoryMenuBus.changeMenu(CategoryMenu.ACC)
            }
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        //소품 이동
        binding.imgCup.setOnClickListener {
            lifecycleScope.launch {
                categoryMenuBus.changeMenu(CategoryMenu.PROP)
            }
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        //잡화 이동
        binding.imgBag.setOnClickListener {
            lifecycleScope.launch {
                categoryMenuBus.changeMenu(CategoryMenu.STUFF)
            }
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        //기타 이동
        binding.imgEtc.setOnClickListener {
            lifecycleScope.launch{
                categoryMenuBus.changeMenu(CategoryMenu.ETC)
            }
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        //더보기 이동
        binding.brandMoreView.setOnClickListener {
            (requireActivity() as MainActivity).goToTab(MainMenuId.BRAND)
        }
        binding.productMoreView.setOnClickListener {
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
        binding.productMoreView2.setOnClickListener {
            (requireActivity() as MainActivity).goToTab(MainMenuId.CATEGORY)
        }
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



}