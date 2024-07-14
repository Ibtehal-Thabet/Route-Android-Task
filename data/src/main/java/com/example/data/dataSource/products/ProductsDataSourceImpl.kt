package com.example.data.dataSource.products

import com.example.data.api.WebService
import com.example.data.dataSourceContract.ProductsDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(
    private val webService: WebService
): ProductsDataSource {

    override suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        val response = webService.getProducts()
        val productList = response.products?.map {
            it?.toProduct()
        }
        return ResultWrapper.Success(productList)
    }

    override suspend fun getSearchedProduct(query: String): ResultWrapper<List<Product?>?> {
        val response = webService.getProducts(query = query)
        val productList = response.products?.map {
            it?.toProduct()
        }
        return ResultWrapper.Success(productList)
    }
}