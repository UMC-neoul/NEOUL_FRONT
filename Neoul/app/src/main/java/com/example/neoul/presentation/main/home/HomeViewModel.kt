package com.example.neoul.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.category.Data
//import com.example.neoul.data.response.product.all.Data
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
    val recommendLiveData = MutableLiveData<List<Data>>()
    val bestLiveData = MutableLiveData<List<Data>>()
    val nameLiveData = MutableLiveData<MyPageInfoData>()

    override fun fetchData() = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        val brandList = brandRepository.getBrandList(jwt)?.map {
            it.toModel()
        } ?: listOf()

        brandLiveData.value = brandList.reversed()

//        val allList = productRepository.allProduct(jwt)?.map {
//            it
//        } ?: listOf()
        val allList = productRepository.categoryProduct(jwt,7,1)?.map {
            it
        } ?: listOf()

        val bestList = productRepository.categoryProduct(jwt,7,2)?.map {
            it
        } ?: listOf()

        recommendLiveData.value = allList
        bestLiveData.value = bestList

        val name = myPageRepository.mypageinfo(jwt)?.data
        nameLiveData.value = name
    }

    fun clickLikeBtn(productId: Int, currentLiked: Boolean) {
        viewModelScope.launch {
            if (!currentLiked) {
                //PRODUCT LIKE PATCH
                productRepository.likeProduct(jwt, productId)
                Log.d("좋아요","좋아요")

            } else {
                //PRODUCT DISLIKE PATCH
                productRepository.dislikeProduct(jwt, productId)
                Log.d("찜해제","찜해제")
            }
        }
    }

}