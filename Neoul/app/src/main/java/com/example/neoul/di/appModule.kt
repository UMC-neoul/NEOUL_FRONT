package com.example.neoul.di

import com.example.neoul.presentation.main.brand.BrandViewModel
import com.example.neoul.presentation.main.category.CategoryViewModel
import com.example.neoul.presentation.main.home.EventViewModel
import com.example.neoul.presentation.main.home.HomeViewModel
import com.example.neoul.presentation.main.my.MyPageViewModel
import com.example.neoul.presentation.main.my.MyPageFragment
import com.example.neoul.presentation.main.story.StoryViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    viewModel { HomeViewModel() }
    viewModel { EventViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { BrandViewModel() }
    viewModel { StoryViewModel() }
    viewModel { MyPageViewModel() }
}