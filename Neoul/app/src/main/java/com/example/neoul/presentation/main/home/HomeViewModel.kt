package com.example.neoul.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.all.Data
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val brandRepository: BrandRepository,private  val productRepository: ProductRepository) : BaseViewModel() {

    val brandLiveData = MutableLiveData<List<BrandItem>>()
    val allLiveData = MutableLiveData<List<Data>>()

    override fun fetchData() = viewModelScope.launch {
        val brandList = brandRepository.getBrandList(Url.AUTH_KEY)?.map {
            it.toModel()
        } ?: listOf()

        brandLiveData.value = brandList

        val allList = productRepository.allProduct(Url.AUTH_KEY)?.map {
            it
        } ?: listOf()

        allLiveData.value = allList
    }


}