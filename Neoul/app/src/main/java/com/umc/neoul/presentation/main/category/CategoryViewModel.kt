package com.umc.neoul.presentation.main.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umc.neoul.data.network.Url
import com.umc.neoul.data.repository.product.ProductRepository
import com.umc.neoul.data.response.product.category.Data
import com.umc.neoul.presentation.BaseViewModel
import com.umc.neoul.util.getJwt
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val productRepository: ProductRepository,
    private val categoryId: Int,
    private val categoryId2: Int
) : BaseViewModel() {

    private var jwt = ""
    val categoryLiveData = MutableLiveData<List<Data>>()

    fun fetchData(option: Int) = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        if(categoryId2 !== 0){
            val categoryList =
                productRepository.categoryProduct(jwt, categoryId, option)?.map {
                    it
                } ?: listOf()
            val categoryList2 =
                productRepository.categoryProduct(jwt, categoryId2, option)?.map {
                    it
                } ?: listOf()
            categoryLiveData.value = categoryList + categoryList2
        } else{
            val categoryList =
                productRepository.categoryProduct(jwt, categoryId, option)?.map {
                    it
                } ?: listOf()
            categoryLiveData.value = categoryList
        }

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