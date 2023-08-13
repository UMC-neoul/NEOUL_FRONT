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

        //accessToken 가져오기
        jwt = "Bearer "+ getJwt()

        val brandList = brandRepository.getBrandList(jwt)?.map {
            it.toModel()
        } ?: listOf()
//        val productList = listOf(
//            Product("", "[핸드메이드] 푸른마음 귀걸이", 16400, 12,""),
//            Product("", "[핸드메이드] 푸른마음 귀걸이", 16400, 12,""),
//            Product("", "[핸드메이드] 푸른마음 귀걸이", 16400, 12,""),
//            Product("", "[핸드메이드] 푸른마음 귀걸이", 16400, 12,""),
//            Product("", "[핸드메이드] 푸른마음 귀걸이", 16400, 12,""),
//

//            )
//        val brandList = listOf(
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//            BrandItem("꼬북이", "바다거북이를 보호하자", "aa",  productList),
//           )

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