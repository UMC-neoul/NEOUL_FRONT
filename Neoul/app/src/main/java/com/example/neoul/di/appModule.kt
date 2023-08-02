package com.example.neoul.di

import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Story
import com.example.neoul.presentation.main.brand.BrandViewModel
import com.example.neoul.presentation.main.brand.detail.BrandDetailViewModel
import com.example.neoul.presentation.main.category.CategoryViewModel
import com.example.neoul.presentation.main.home.EventViewModel
import com.example.neoul.presentation.main.home.HomeViewModel
import com.example.neoul.presentation.main.my.MyPageViewModel
import com.example.neoul.presentation.main.my.MyPageFragment
import com.example.neoul.presentation.main.story.StoryViewModel
import com.example.neoul.presentation.main.story.detail.StoryDetailViewModel
import com.example.neoul.presentation.product.ProductViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //network
    single { providerGsonConvertFactory() }
    single { buildOkHttpClint() }

    //VM
    viewModel { HomeViewModel() }
    viewModel { EventViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { BrandViewModel() }
    viewModel { (brand: BrandItem) -> BrandDetailViewModel(brand) }
    viewModel { StoryViewModel() }

    viewModel { MyViewModel() }
    viewModel { (story: Story) -> StoryDetailViewModel(story) }
    //viewModel { MyPageViewModel() }
    viewModel { (product: GoodsItem) -> ProductViewModel(product) }

}