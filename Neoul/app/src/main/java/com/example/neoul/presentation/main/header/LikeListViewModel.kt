package com.example.neoul.presentation.main.header

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.like.product.Data
import com.example.neoul.data.response.like.product.LikedProduct
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch


class LikeListViewModel(private val productRepository: ProductRepository,private val brandRepository: BrandRepository) : BaseViewModel() {

    val likedProductLiveData = MutableLiveData<Data>()
    val likedBrandLiveData = MutableLiveData<com.example.neoul.data.response.like.brand.Data>()
    override fun fetchData() = viewModelScope.launch {
        val likeProductList = productRepository.likeProductList(Url.AUTH_KEY)
        val likeBrandList = brandRepository.likeBrandList(Url.AUTH_KEY)

        likedProductLiveData.value = likeProductList
        likedBrandLiveData.value = likeBrandList

    }
}