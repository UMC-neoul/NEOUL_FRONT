package com.example.neoul.presentation.main.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.product.ProductRepository
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val productRepository: ProductRepository,
    private val categoryId: Int,
    private val option: Int
) : BaseViewModel() {

    val categoryLiveData = MutableLiveData<List<Data>>()

    override fun fetchData() = viewModelScope.launch {
        val categoryList =
            productRepository.categoryProduct(Url.AUTH_KEY, categoryId, option)?.map {
                it
            } ?: listOf()

        categoryLiveData.value = categoryList
    }
}