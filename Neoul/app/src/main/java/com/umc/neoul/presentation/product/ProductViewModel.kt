package com.umc.neoul.presentation.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umc.neoul.data.model.Product
import com.umc.neoul.data.network.Url
import com.umc.neoul.data.repository.product.ProductRepository
import com.umc.neoul.data.response.like.history.request.HistoryRequest
import com.umc.neoul.presentation.BaseViewModel
import com.umc.neoul.presentation.main.brand.detail.BrandDetailState
import com.umc.neoul.util.UserCode.jwt
import com.umc.neoul.util.getJwt
import kotlinx.coroutines.launch

class ProductViewModel(
    private val product: Product,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private var jwt = ""

    val productLikedLiveData = MutableLiveData<Boolean>(null)
    val productStateLiveData = MutableLiveData<ProductState>(ProductState.Uninitialized)

    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비회원일 경우 로그인 페이지로)
        if (getJwt().isNullOrEmpty()){
            productStateLiveData.value = ProductState.NotAuth
            return@launch
        }else{
            jwt = getJwt().toString()
        }

        //찜한 상품인지 확인 (PRODUCT LIKE LIST GET) -> 찜 버튼 적용
        val liked = productRepository.likeProductList(jwt)?.likedProduct?.any{
            it.productId == product.productId
        }
        productLikedLiveData.value = liked
        if (product.productUrl.isBlank()){
            val productUrlApi = productRepository.getProductDetail(jwt,product.productId) ?:""
            product.productUrl = productUrlApi
        }

        productStateLiveData.value = ProductState.Success(product)
    }

    fun clickLikeBtn(){
        viewModelScope.launch {
            if (productLikedLiveData.value == false) {
                //PRODUCT LIKE PATCH
                productRepository.likeProduct(jwt, product.productId)
                productLikedLiveData.value = true
            } else {
                //PRODUCT DISLIKE PATCH
                productRepository.dislikeProduct(jwt, product.productId)
                productLikedLiveData.value = false
            }
        }
    }

    fun postViewHistory(currentTime: String) {
        viewModelScope.launch {
            //HISTORY POST
            productRepository.postViewHistory(jwt, HistoryRequest(currentTime,product.productId))
        }
    }
}