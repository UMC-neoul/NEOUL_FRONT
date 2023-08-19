package com.example.neoul.presentation.main.header

import android.os.Bundle
import com.example.neoul.databinding.ActivityLikeListBinding

import com.example.neoul.presentation.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class LikeListActivity : BaseActivity<LikeListViewModel, ActivityLikeListBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding(): ActivityLikeListBinding =
        ActivityLikeListBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabAdapter = LikeListTabAdapter(this)
        binding.viewPager.adapter = tabAdapter

        val tabTitleArray = arrayOf(
            "찜한 상품",
            "찜한 브랜드"
        )
        TabLayoutMediator(binding.layoutTab, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        binding.imgClose.setOnClickListener {
            finish()
        }



    }
}