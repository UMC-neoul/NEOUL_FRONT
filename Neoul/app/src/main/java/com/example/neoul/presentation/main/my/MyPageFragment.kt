package com.example.neoul.presentation.main.my

import com.example.neoul.databinding.FragmentMypageBinding
import com.example.neoul.presentation.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<MyPageViewModel, FragmentMypageBinding>() {

    override val viewModel by viewModel<MyPageViewModel>()

    override fun getViewBinding() = FragmentMypageBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = MyPageFragment()

        const val TAG = "MyFragment"
    }
}