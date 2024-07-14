package com.example.domain.repositories

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product

interface ProductsRepository {

   suspend fun getProducts(): ResultWrapper<List<Product?>?>

   suspend fun getSearchedProduct(query: String): ResultWrapper<List<Product?>?>
}