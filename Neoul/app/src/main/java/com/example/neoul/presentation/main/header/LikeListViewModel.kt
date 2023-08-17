package com.example.neoul.presentation.main.header

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.like.product.Data
import com.example.neoul.data.response.like.product.LikedProduct
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch


class LikeListViewModel(private val productRepository: ProductRepository) : BaseViewModel() {

    val likedProductCntLiveData = MutableLiveData<Data>()

    override fun fetchData() = viewModelScope.launch {
        val likeProductList = productRepository.likeProductList(Url.AUTH_KEY)

        likedProductCntLiveData.value = likeProductList

    }
}