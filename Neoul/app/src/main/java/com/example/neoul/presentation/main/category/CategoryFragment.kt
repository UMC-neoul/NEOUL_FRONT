package com.example.neoul.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neoul.R
import com.example.neoul.databinding.FragmentCategoryBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.BrandFragment
import com.example.neoul.presentation.main.header.LikeListActivity
import com.example.neoul.presentation.main.header.SearchActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel> {
        parametersOf(1, 1)
    }

    override fun getViewBinding() = FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.categoryLiveData.observe(viewLifecycleOwner) {


    }

    companion object {
        fun newInstance() = CategoryFragment()

        const val TAG = "CategoryFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.imgSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))

        }
        binding.imgHeart.setOnClickListener {
            startActivity(Intent(requireContext(), LikeListActivity::class.java))

        }
        val tabAdapter = TabAdapter(this)
        binding.viewPager.adapter = tabAdapter

        val tabTitleArray = arrayOf(
            "의류",
            "소품",
            "악세사리",
            "잡화",
            "기타"
        )

        TabLayoutMediator(binding.layoutTab, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]

        }.attach()

        return binding.root
    }
}