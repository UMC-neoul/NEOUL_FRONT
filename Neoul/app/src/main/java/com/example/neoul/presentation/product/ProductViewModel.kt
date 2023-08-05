package com.example.neoul.presentation.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Product
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class ProductViewModel(
    private val product: Product
) : BaseViewModel() {

    val productLikedLiveData = MutableLiveData<Boolean>(product.liked)
    val productStateLiveData = MutableLiveData<ProductState>(ProductState.Uninitialized)

    override fun fetchData() = viewModelScope.launch {
        productStateLiveData.value = ProductState.Success(product)
    }

    fun clickLikeBtn(){
        product.liked = !product.liked
        productLikedLiveData.value = product.liked
        //DB 연결 예정
    }
}