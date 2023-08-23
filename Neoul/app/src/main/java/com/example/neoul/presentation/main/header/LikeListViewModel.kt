package com.example.neoul.presentation.main.header

import android.net.Uri
import android.util.Log
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


class LikeListViewModel(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository
) : BaseViewModel() {

    private var jwt = ""
    val likedProductLiveData = MutableLiveData<Data>()
    val likedBrandLiveData = MutableLiveData<com.example.neoul.data.response.like.brand.Data>()
    override fun fetchData() = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        val likeProductList = productRepository.likeProductList(jwt)
        val likeBrandList = brandRepository.likeBrandList(jwt)

        likeProductList?.likedProduct?.forEach { likedProduct ->
            likedProduct.liked = true
        }
        likedProductLiveData.value = likeProductList
        likedBrandLiveData.value = likeBrandList

    }

    fun clickLikeBtn(productId: Int, currentLiked: Boolean) {
        viewModelScope.launch {
            if (!currentLiked) {
                //PRODUCT LIKE PATCH
                productRepository.likeProduct(jwt, productId)
                Log.d("좋아요", "좋아요")

            } else {
                //PRODUCT DISLIKE PATCH
                productRepository.dislikeProduct(jwt, productId)
                Log.d("찜해제", "찜해제")
            }
        }
    }
}