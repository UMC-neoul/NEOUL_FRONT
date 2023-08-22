package com.example.neoul.presentation.main.header

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.Product
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.all.dataToProduct
import com.example.neoul.data.response.product.recent.Data
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class SearchViewModel(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository
) : BaseViewModel() {

    private var jwt = ""
    val recentLiveData = MutableLiveData<List<Data>>()
    val searchBrandLiveData = MutableLiveData<List<BrandItem>>()
    val searchProductLiveData = MutableLiveData<List<Product>>()
    val searchStateLiveData =MutableLiveData<SearchState>(SearchState.SearchBefore)
    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비회원일때는 my fragment 로 이동)
        if (getJwt().isNullOrEmpty()){
            searchStateLiveData.value = SearchState.NoAuth
            return@launch
        }else{
            jwt = getJwt().toString()
            searchStateLiveData.value = SearchState.SearchBefore
        }

        val recentProductList = productRepository.recentProductList(jwt)

        recentLiveData.value = recentProductList
    }

    fun search(word : String){
        viewModelScope.launch {
            val brandList = brandRepository.getBrandList(jwt)?.map {
                it.toModel()
            }?.filter {
                it.name.contains(word) or it.content.contains(word)
            }?: listOf()

            val productList = productRepository.allProduct(jwt)?.filter {
                it.productName.contains(word)
            }?.map {
              dataToProduct(it)
            }?: listOf()

            searchStateLiveData.value = SearchState.SearchAfter(brandList.size, productList.size)

            searchProductLiveData.value = productList
            searchBrandLiveData.value = brandList
        }
    }
}