package com.example.neoul.presentation.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neoul.databinding.FragmentCategoryBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.BrandFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding() = FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = CategoryFragment()

        const val TAG = "CategoryFragment"
    }

    private lateinit var viewBinding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoryBinding.inflate(layoutInflater)

        val tabAdapter = TabAdapter(this)
        viewBinding.viewPager.adapter = tabAdapter

        val tabTitleArray = arrayOf(
            "의류",
            "소품",
            "악세사리",
            "잡화",
            "기타"
        )

        TabLayoutMediator(viewBinding.layoutTab, viewBinding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        return viewBinding.root
    }
}