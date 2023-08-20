package com.example.neoul.presentation.main.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val productRepository: ProductRepository,
    private val categoryId: Int
) : BaseViewModel() {


    private var jwt =""
    val categoryLiveData = MutableLiveData<List<Data>>()

    fun fetchData(option: Int) = viewModelScope.launch {

        jwt = getJwt().takeIf { !it.isNullOrEmpty() } ?: Url.AUTH_KEY

        val categoryList =
            productRepository.categoryProduct(jwt, categoryId, option)?.map {
                it
            } ?: listOf()
        Log.d("아오",categoryList.toString())

        categoryLiveData.value = categoryList
    }
}