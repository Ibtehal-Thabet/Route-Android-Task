package com.example.domain.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository) {

    suspend fun invokeProductList(): ResultWrapper<List<Product?>?> {
        return repository.getProducts()
    }

    suspend fun invokeSearchedProduct(query: String): ResultWrapper<List<Product?>?> {
        return repository.getSearchedProduct(query = query)
    }
}