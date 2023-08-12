package com.example.neoul.data.response.brand.list

import com.example.neoul.data.model.BrandItem

data class Data(
    val brandId: Int,
    val categoryVId: Int,
    val categoryVName: String,
    val intro: String,
    val name: String,
    val productList: List<Product>,
    val profileImg: String
){

    fun toModel() = BrandItem(
        id =brandId,
        name = name,
        content = intro,
        image = profileImg,
        productList = productList.map {
            com.example.neoul.data.model.Product(
                deliveryInfo = it.deliveryInfo,
                name = it.name,
                price = it.price,
                productId = it.productId,
                productUrl = it.productUrl,
                productImg = if(it.productImgList.isNotEmpty()){
                    it.productImgList.first()
                }else{
                    ""
                }
            )
        }
    )
}