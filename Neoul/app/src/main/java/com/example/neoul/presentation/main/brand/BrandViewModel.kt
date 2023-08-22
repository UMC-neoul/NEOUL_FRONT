package com.example.neoul.presentation.main.brand

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.brand.BrandRepository
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.util.UserCode.jwt
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class BrandViewModel(
    private val brandRepository: BrandRepository
) : BaseViewModel() {

    private var jwt =""

    val brandLiveData = MutableLiveData<List<BrandItem>>()
    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비 회원도 일단 가져오기)
        if (getJwt().isNullOrEmpty()){
            jwt = Url.AUTH_KEY
        }else{
            jwt = getJwt().toString()
        }

        val brandList = brandRepository.getBrandList(jwt)?.map {
            it.toModel()
        }?.reversed() ?: listOf()

        brandLiveData.value = brandList
    }

    //최신순 정렬
    fun recentSortClick(){
        brandLiveData.value = brandLiveData.value?.reversed()
    }

    //추천순 정렬
    fun recommendSortClick(){
        fetchData()
    }
}