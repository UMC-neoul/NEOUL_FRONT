package com.example.neoul.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val brandRepository: BrandRepository) : BaseViewModel() {

    val brandLiveData = MutableLiveData<List<BrandItem>>()

    override fun fetchData() = viewModelScope.launch {
        val brandList = brandRepository.getBrandList(Url.AUTH_KEY)?.map {
            it.toModel()
        } ?: listOf()

        brandLiveData.value = brandList
    }


}