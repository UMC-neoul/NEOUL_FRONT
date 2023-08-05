package com.example.neoul.data.repository.brand

import com.example.neoul.data.network.BrandApiService
import com.example.neoul.data.response.brand.list.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultBrandRepository(
    private val brandAPiService : BrandApiService,
    private val ioDispatcher : CoroutineDispatcher
):BrandRepository {
    override suspend fun getBrandList(): List<Data>? = withContext(ioDispatcher) {
        val response = brandAPiService.getBrandListApi()
        if (response.isSuccessful){
            response.body()?.data ?: listOf()
        }else{
            listOf()
        }
    }

    override suspend fun getBrandDetail(
        brandId: Int
    ): com.example.neoul.data.response.brand.detail.Data?
    = withContext(ioDispatcher){
        val response = brandAPiService.getBrandDetailApi(brandId)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }
}