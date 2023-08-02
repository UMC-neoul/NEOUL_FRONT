package com.example.neoul.presentation.main.brand

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Story
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class BrandViewModel : BaseViewModel() {

    val brandLiveData = MutableLiveData<List<BrandItem>>()
    override fun fetchData() = viewModelScope.launch {
        val productList = listOf(
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 빨간마을 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),

            )
        val brandList = listOf(
            BrandItem("나이키", "스포츠", "aa", "2023.03.01", productList),
            BrandItem("아디다스", "스포츠", "aa", "2023.03.01", productList),
            BrandItem("keen", "스포츠", "aa", "2023.03.01", productList),
            BrandItem("나이키", "스포츠", "aa", "2023.03.01", productList),
            BrandItem("나이키", "스포츠", "aa", "2023.03.01", productList),
            BrandItem("나이키", "스포츠", "aa", "2023.03.01", productList),
        )

        brandLiveData.value = brandList
    }

    //정렬 버튼 눌렀을 때
    fun clickSortBtn(){

    }
}