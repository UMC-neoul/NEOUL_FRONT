package com.example.neoul.presentation.main.my

import com.example.neoul.databinding.FragmentMyBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding() = FragmentMyBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = MyFragment()

        const val TAG = "MyFragment"
    }
}