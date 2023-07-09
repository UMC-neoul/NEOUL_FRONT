package com.example.neoul.presentation.main.story

import android.content.Intent
import com.example.neoul.databinding.FragmentStoryBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import com.example.neoul.presentation.main.story.detail.StoryDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {

    override val viewModel by viewModel<StoryViewModel>()

    override fun getViewBinding() = FragmentStoryBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        binding.banner.setOnClickListener {
            startActivity(Intent(requireContext(), StoryDetailActivity::class.java))
        }
    }
    override fun observeDate() {}

    companion object {
        fun newInstance() = StoryFragment()

        const val TAG = "StoryFragment"
    }
}