package com.example.data.dataSourceContract

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product

interface ProductsDataSource {
    suspend fun getProducts(): ResultWrapper<List<Product?>?>

    suspend fun getSearchedProduct(query: String): ResultWrapper<List<Product?>?>
}