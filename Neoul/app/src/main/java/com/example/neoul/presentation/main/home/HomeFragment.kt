package com.example.neoul.presentation.main.home

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
}