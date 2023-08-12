package com.example.neoul.presentation.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.Product
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.like.history.request.HistoryRequest
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class ProductViewModel(
    private val product: Product,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    val productLikedLiveData = MutableLiveData<Boolean>(null)
    val productStateLiveData = MutableLiveData<ProductState>(ProductState.Uninitialized)

    override fun fetchData() = viewModelScope.launch {
        //찜한 상품인지 확인 (PRODUCT LIKE LIST GET) -> 찜 버튼 적용
        val liked = productRepository.likeProductList(Url.AUTH_KEY)?.likedProduct?.any{
            it.productId == product.productId
        }
        productLikedLiveData.value = liked

        productStateLiveData.value = ProductState.Success(product)
    }

    fun clickLikeBtn(){
        viewModelScope.launch {
            if (productLikedLiveData.value == false) {
                //PRODUCT LIKE PATCH
                productRepository.likeProduct(Url.AUTH_KEY, product.productId)
                productLikedLiveData.value = true
            } else {
                //PRODUCT DISLIKE PATCH
                productRepository.dislikeProduct(Url.AUTH_KEY, product.productId)
                productLikedLiveData.value = false
            }
        }
    }

    fun postViewHistory(currentTime: String) {
        viewModelScope.launch {
            //HISTORY POST
            productRepository.postViewHistory(Url.AUTH_KEY, HistoryRequest(currentTime,product.productId))
        }
    }
}