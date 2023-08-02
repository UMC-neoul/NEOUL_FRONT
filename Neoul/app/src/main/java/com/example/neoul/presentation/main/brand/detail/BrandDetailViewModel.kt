package com.example.neoul.presentation.main.brand.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class BrandDetailViewModel(
    private val brand: BrandItem
) : BaseViewModel() {

    val brandDetailStateLiveData = MutableLiveData<BrandDetailState>(BrandDetailState.Uninitialized)
    val brandLikedLiveData = MutableLiveData<Boolean>(brand.liked)
    val productListLiveData = MutableLiveData<List<GoodsItem>>()

    override fun fetchData() = viewModelScope.launch {
        productListLiveData.value = brand.productList
        brandDetailStateLiveData.value = BrandDetailState.Success(brand)
    }

    //찜 버튼을 눌렀을 때
    fun clickLikeBtn(){
        brand.liked = !brand.liked
        brandLikedLiveData.value = brand.liked
        //DB 연결 예정
    }

    //정렬 버튼을 눌렀을 때
    fun clickSortBtn(){

    }
}