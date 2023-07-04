package com.example.neoul.presentation.main.category

import com.example.neoul.databinding.FragmentCategoryBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.BrandFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding() = FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = CategoryFragment()

        const val TAG = "CategoryFragment"
    }
}