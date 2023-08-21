package com.example.neoul.presentation.main.header

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.recent.Data
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class SearchViewModel(private val productRepository: ProductRepository) : BaseViewModel() {

    private var jwt = ""
    val recentLiveData = MutableLiveData<List<Data>>()
    override fun fetchData() = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY
        val recentProductList = productRepository.recentProductList(jwt)

        recentLiveData.value = recentProductList
    }
}