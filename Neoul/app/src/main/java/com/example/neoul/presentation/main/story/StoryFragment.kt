package com.example.neoul.presentation.main.story

import com.example.neoul.databinding.FragmentStoryBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import org.koin.android.viewmodel.ext.android.viewModel

class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {

    override val viewModel by viewModel<StoryViewModel>()

    override fun getViewBinding() = FragmentStoryBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = StoryFragment()

        const val TAG = "StoryFragment"
    }
}