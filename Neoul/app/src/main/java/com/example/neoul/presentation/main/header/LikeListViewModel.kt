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
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch


class LikeListViewModel(private val productRepository: ProductRepository,private val brandRepository: BrandRepository) : BaseViewModel() {

    private var jwt =""
    val likedProductLiveData = MutableLiveData<Data>()
    val likedBrandLiveData = MutableLiveData<com.example.neoul.data.response.like.brand.Data>()
    override fun fetchData() = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        val likeProductList = productRepository.likeProductList(jwt)
        val likeBrandList = brandRepository.likeBrandList(jwt)

        likedProductLiveData.value = likeProductList
        likedBrandLiveData.value = likeBrandList

    }
}