package com.umc.neoul.data.repository.brand

import com.umc.neoul.data.network.BrandApiService
import com.umc.neoul.data.response.brand.list.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultBrandRepository(
    private val brandAPiService : BrandApiService,
    private val ioDispatcher : CoroutineDispatcher
):BrandRepository {
    override suspend fun getBrandList(accessToken: String): List<Data>? = withContext(ioDispatcher) {
        val response = brandAPiService.getBrandListApi(accessToken)
        if (response.isSuccessful){
            response.body()?.data ?: listOf()
        }else{
            listOf()
        }
    }

    override suspend fun getBrandDetail(
        accessToken: String,
        brandId: Int
    ): com.umc.neoul.data.response.brand.detail.Data?
    = withContext(ioDispatcher){
        val response = brandAPiService.getBrandDetailApi(accessToken,brandId)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }

    override suspend fun likeBrand(
        accessToken: String,
        brandId: Int
    ) {
        withContext(ioDispatcher){
           brandAPiService.likeBrandApi(accessToken,brandId)
        }
    }

    override suspend fun dislikeBrand(
        accessToken: String,
        brandId: Int
    ) {
        withContext(ioDispatcher){
            brandAPiService.dislikeBrandApi(accessToken,brandId).body()
        }
    }

    override suspend fun likeBrandList(accessToken: String): com.umc.neoul.data.response.like.brand.Data?
    = withContext(ioDispatcher){
        val response = brandAPiService.likeBrandListApi(accessToken)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }
}