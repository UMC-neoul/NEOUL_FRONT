package com.umc.neoul.presentation.main.brand.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umc.neoul.data.model.BrandItem
import com.umc.neoul.data.model.Product
import com.umc.neoul.data.repository.brand.BrandRepository
import com.umc.neoul.presentation.BaseViewModel
import com.umc.neoul.util.getJwt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BrandDetailViewModel(
    private val brand: BrandItem,
    private val brandRepository: BrandRepository
) : BaseViewModel() {

    private var jwt = ""

    val brandDetailStateLiveData = MutableLiveData<BrandDetailState>(BrandDetailState.Uninitialized)
    val brandLikedLiveData = MutableLiveData<Boolean>(null)
    val productListLiveData = MutableLiveData<List<Product>>()

    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비회원일때는 my fragment 로 이동)
        if (getJwt().isNullOrEmpty()){
            brandDetailStateLiveData.value = BrandDetailState.NotAuth
            return@launch
        }else{
            jwt = getJwt().toString()
        }

        //찜한 브랜지인지 확인 (BRAND LIKE LIST GET) -> 찜 버튼 적용
        val liked = brandRepository.likeBrandList(jwt)?.likedBrands?.any {
            it.brandId == brand.id
        }
        brandLikedLiveData.value = liked

        //BRAND DETAIL GET
        brandRepository.getBrandDetail(jwt, brand.id)?.toModel()?.let {
            // null 나왔을때 예외처리
            productListLiveData.value = it.productList
            brandDetailStateLiveData.value = BrandDetailState.Success(it)
        } ?: kotlin.run {
            productListLiveData.value = brand.productList
            brandDetailStateLiveData.value = BrandDetailState.Failure
        }
    }

    //찜 버튼을 눌렀을 때
    fun clickLikeBtn() {
        viewModelScope.launch {
            if (brandLikedLiveData.value == false) {
                //BRAND LIKE PATCH
                brandRepository.likeBrand(jwt, brand.id)
                brandLikedLiveData.value = true
            } else {
                //BRAND DISLIKE PATCH
                brandRepository.dislikeBrand(jwt, brand.id)
                brandLikedLiveData.value = false
            }
            //DB 연결 예정
        }
    }

    //낮은 가격순 버튼을 눌렀을 때
    fun lowPriceSortClick() {
        productListLiveData.value = productListLiveData.value?.sortedBy {
            it.price
        }
    }

    //낮은 가격순 버튼을 눌렀을 때
    fun highPriceSortClick() {
        productListLiveData.value = productListLiveData.value?.sortedBy {
            it.price
        }?.reversed()
    }

    //최근순 버튼을 눌렀을 때
    suspend fun recentSortClick() {
        val list=
        withContext(Dispatchers.IO) {
            brandRepository.getBrandDetail(jwt, brand.id)?.toModel()?.let {
                it.productList
            } ?: listOf()
        }
        productListLiveData.value = list.reversed()
    }

    //추천순 버튼을 눌렀을 때
    fun recommendSortClick() {
        fetchData()
    }
}