package com.example.neoul.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.all.Data
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.presentation.main.my.Info.MyPageInfoData
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class HomeViewModel(
    private val brandRepository: BrandRepository,
    private val productRepository: ProductRepository,
    private val myPageRepository: MyPageRepository
) : BaseViewModel() {

    private var jwt = ""

    val productLikedLiveData = MutableLiveData<Boolean>(null)
    val brandLiveData = MutableLiveData<List<BrandItem>>()
    val allLiveData = MutableLiveData<List<Data>>()
    val nameLiveData = MutableLiveData<MyPageInfoData>()

    override fun fetchData() = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        val brandList = brandRepository.getBrandList(jwt)?.map {
            it.toModel()
        } ?: listOf()

        brandLiveData.value = brandList.reversed()

        val allList = productRepository.allProduct(jwt)?.map {
            it
        } ?: listOf()

        allLiveData.value = allList.reversed()

        val name = myPageRepository.mypageinfo(jwt)?.data
        nameLiveData.value = name
    }

    fun clickLikeBtn(productId: Int) {
        viewModelScope.launch {
            if (productLikedLiveData.value == false) {
                //PRODUCT LIKE PATCH
                productRepository.likeProduct(jwt, productId)
                productLikedLiveData.value = true
            } else {
                //PRODUCT DISLIKE PATCH
                productRepository.dislikeProduct(jwt, productId)
                productLikedLiveData.value = false
            }
        }
    }

}