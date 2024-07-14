package com.example.data.repository.products

import com.example.data.dataSourceContract.ProductsDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val dataSource: ProductsDataSource
): ProductsRepository {

    override suspend fun getProducts(): ResultWrapper<List<Product?>?> {
        return dataSource.getProducts()

    }

    override suspend fun getSearchedProduct(query: String): ResultWrapper<List<Product?>?> {
        return dataSource.getSearchedProduct(query = query)
    }
}