package com.umc.neoul.presentation.main.header

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.umc.neoul.databinding.ActivityLikeListBinding

import com.umc.neoul.presentation.BaseActivity
import com.umc.neoul.presentation.main.MainMenuId
import com.umc.neoul.util.MainMenuBus
import com.umc.neoul.util.UserCode.jwt
import com.umc.neoul.util.getJwt
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LikeListActivity : BaseActivity<LikeListViewModel, ActivityLikeListBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding(): ActivityLikeListBinding =
        ActivityLikeListBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    private val mainMenuBus by inject<MainMenuBus>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (getJwt().isNullOrEmpty()){
            Toast.makeText(this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_LONG).show()
            lifecycleScope.launch {
                mainMenuBus.changMenu(MainMenuId.My)
                finish()
            }
        }

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