package com.example.neoul.di

import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.Product
import com.example.neoul.data.model.Story
import com.example.neoul.data.preference.ApplicationPreferenceManager
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.brand.DefaultBrandRepository
import com.example.neoul.data.repository.login.DefaultLoginRepository
import com.example.neoul.data.repository.login.LoginRepository
import com.example.neoul.data.repository.mypage.DefaultMyPageRepository
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.data.repository.product.DefaultProductRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.repository.signup.DefaultSignupRepository
import com.example.neoul.data.repository.signup.SignupRepository
import com.example.neoul.data.repository.story.DefaultStoryRepository
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.data.response.product.all.Data
import com.example.neoul.presentation.main.brand.BrandViewModel
import com.example.neoul.presentation.main.brand.detail.BrandDetailViewModel
import com.example.neoul.presentation.main.category.CategoryViewModel
import com.example.neoul.presentation.main.header.LikeListViewModel
import com.example.neoul.presentation.main.header.SearchViewModel
import com.example.neoul.presentation.main.home.EventViewModel
import com.example.neoul.presentation.main.home.HomeViewModel
import com.example.neoul.presentation.main.my.MyPageViewModel
import com.example.neoul.presentation.main.story.StoryViewModel
import com.example.neoul.presentation.main.story.detail.StoryDetailViewModel
import com.example.neoul.presentation.product.ProductViewModel
import com.example.neoul.util.MainMenuBus
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //network
    single { provideNeoulRetrofit(get(), get()) }
    single { providerGsonConvertFactory() }
    single { buildOkHttpClint() }

    //Api
    single { provideStoryApiService(get()) }
    single { provideBrandApiService(get()) }
    single { provideProductApiService(get()) }

    //loginApi
    single { provideLoginApiService(get()) }

    single<LoginRepository> { DefaultLoginRepository(get(), get()) }


    //SignUpApi
    single { provideSignUpApiService(get()) }

    single<SignupRepository> { DefaultSignupRepository(get(), get()) }

    //MyPageApi
    single { provideMyPageApiService(get()) }
    single<MyPageRepository> { DefaultMyPageRepository(get(), get()) }


    //Repository
    single<StoryRepository> { DefaultStoryRepository(get(), get()) }
    single<BrandRepository> { DefaultBrandRepository(get(), get()) }
    single<ProductRepository> { DefaultProductRepository(get(), get()) }

    //util
    single { ApplicationPreferenceManager(androidApplication()) }
    single { MainMenuBus() }

    //VM
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { EventViewModel() }
    viewModel { (categoryId: Int) ->
        CategoryViewModel(get(), categoryId)
    }
    viewModel { BrandViewModel(get()) }
    viewModel { (brand: BrandItem) -> BrandDetailViewModel(brand, get()) }
    viewModel { StoryViewModel(get()) }
    viewModel { (story: Story) -> StoryDetailViewModel(story, get()) }
    viewModel { MyPageViewModel(get()) }
    viewModel { (product: Product) -> ProductViewModel(product, get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { LikeListViewModel(get(), get()) }

}