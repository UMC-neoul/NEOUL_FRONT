package com.example.neoul.presentation.main.brand

import com.example.neoul.databinding.FragmentBrandBinding
import com.example.neoul.presentation.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class BrandFragment : BaseFragment<BrandViewModel, FragmentBrandBinding>() {

    override val viewModel by viewModel<BrandViewModel>()

    override fun getViewBinding() = FragmentBrandBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = BrandFragment()

        const val TAG = "BrandFragment"
    }
}