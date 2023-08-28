package com.umc.neoul.di

import com.umc.neoul.data.model.BrandItem
import com.umc.neoul.data.model.Product
import com.umc.neoul.data.model.Story
import com.umc.neoul.data.preference.ApplicationPreferenceManager
import com.umc.neoul.data.repository.brand.BrandRepository
import com.umc.neoul.data.repository.brand.DefaultBrandRepository
import com.umc.neoul.data.repository.login.DefaultLoginRepository
import com.umc.neoul.data.repository.login.LoginRepository
import com.umc.neoul.data.repository.mypage.DefaultMyPageRepository
import com.umc.neoul.data.repository.mypage.MyPageRepository
import com.umc.neoul.data.repository.product.DefaultProductRepository
import com.umc.neoul.data.repository.product.ProductRepository
import com.umc.neoul.data.repository.signup.DefaultSignupRepository
import com.umc.neoul.data.repository.signup.SignupRepository
import com.umc.neoul.data.repository.story.DefaultStoryRepository
import com.umc.neoul.data.repository.story.StoryRepository
import com.umc.neoul.presentation.main.brand.BrandViewModel
import com.umc.neoul.presentation.main.brand.detail.BrandDetailViewModel
import com.umc.neoul.presentation.main.category.CategoryViewModel
import com.umc.neoul.presentation.main.header.LikeListViewModel
import com.umc.neoul.presentation.main.header.SearchViewModel
import com.umc.neoul.presentation.main.home.EventViewModel
import com.umc.neoul.presentation.main.home.HomeViewModel
import com.umc.neoul.presentation.main.my.MyPageViewModel
import com.umc.neoul.presentation.main.story.StoryViewModel
import com.umc.neoul.presentation.main.story.detail.StoryDetailViewModel
import com.umc.neoul.presentation.product.ProductViewModel
import com.umc.neoul.util.CategoryMenuBus
import com.umc.neoul.util.MainMenuBus
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
    single { CategoryMenuBus() }

    //VM
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { EventViewModel() }
    viewModel { (categoryId: Int, categoryId2: Int) ->
        CategoryViewModel(get(), categoryId,categoryId2)
    }
    viewModel { BrandViewModel(get()) }
    viewModel { (brand: BrandItem) -> BrandDetailViewModel(brand, get()) }
    viewModel { StoryViewModel(get()) }
    viewModel { (story: Story) -> StoryDetailViewModel(story, get()) }
    viewModel { MyPageViewModel(get()) }
    viewModel { (product: Product) -> ProductViewModel(product, get()) }
    viewModel { SearchViewModel(get() ,get()) }
    viewModel { LikeListViewModel(get(), get()) }

}