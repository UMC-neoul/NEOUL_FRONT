package com.example.neoul.di

import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.Product
import com.example.neoul.data.model.Story
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.brand.DefaultBrandRepository
import com.example.neoul.data.repository.login.DefultLoginRepository
import com.example.neoul.data.repository.login.LoginRepository
import com.example.neoul.data.repository.signup.DefultSignupRepository
import com.example.neoul.data.repository.signup.SignupRepository
import com.example.neoul.data.repository.story.DefaultStoryRepository
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.presentation.main.brand.BrandViewModel
import com.example.neoul.presentation.main.brand.detail.BrandDetailViewModel
import com.example.neoul.presentation.main.category.CategoryViewModel
import com.example.neoul.presentation.main.home.EventViewModel
import com.example.neoul.presentation.main.home.HomeViewModel
import com.example.neoul.presentation.main.my.MyPageViewModel
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
    single { provideNeoulRetrofit(get(), get()) }
    single { providerGsonConvertFactory() }
    single { buildOkHttpClint() }

    //Api
    single { provideStoryApiService(get()) }
    single { provideBrandApiService(get()) }
    single { provideProductApiService(get()) }


    single { provideLoginApiService(get()) }
    single<LoginRepository>{ DefultLoginRepository(get(), get()) }

    single { provideSignUpApiService(get()) }
    single<SignupRepository>{DefultSignupRepository(get(),get())}


    //Repository
    single<StoryRepository> { DefaultStoryRepository(get(), get()) }
    single<BrandRepository> { DefaultBrandRepository(get(), get()) }
    single<ProductRepository> { DefaultProductRepository(get(), get()) }

    //VM
    viewModel { HomeViewModel() }
    viewModel { EventViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { BrandViewModel(get()) }
    viewModel { (brand: BrandItem) -> BrandDetailViewModel(brand, get()) }
    viewModel { StoryViewModel(get()) }
    viewModel { (story: Story) -> StoryDetailViewModel(story, get()) }
    viewModel { MyPageViewModel() }
    viewModel { (product: Product) -> ProductViewModel(product, get()) }

}