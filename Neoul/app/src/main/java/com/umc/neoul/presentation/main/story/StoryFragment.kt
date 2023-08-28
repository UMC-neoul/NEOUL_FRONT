package com.umc.neoul.presentation.main.story

import android.content.Intent
import com.umc.neoul.adapter.StoryRVAdapter
import com.umc.neoul.databinding.FragmentStoryBinding
import com.umc.neoul.presentation.BaseFragment
import com.umc.neoul.presentation.main.header.LikeListActivity
import com.umc.neoul.presentation.main.header.SearchActivity
import com.umc.neoul.presentation.main.story.detail.StoryDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {

    override val viewModel by viewModel<StoryViewModel>()

    private val adapter by lazy {
        StoryRVAdapter {
            startActivity(StoryDetailActivity.newIntent(requireContext(), it))
        }
    }

    override fun getViewBinding() = FragmentStoryBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        binding.storyRV.adapter = adapter
        binding.searchBtn.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.likeListBtn.setOnClickListener {
            startActivity(Intent(requireContext(), LikeListActivity::class.java))
        }
    }

    override fun observeDate() = viewModel.storyLiveData.observe(viewLifecycleOwner) {
        adapter.setList(it)
    }

    companion object {
        fun newInstance() = StoryFragment()

        const val TAG = "StoryFragment"
        const val STORY_KEY = "story"
    }
}