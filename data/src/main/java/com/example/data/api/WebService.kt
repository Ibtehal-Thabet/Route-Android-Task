package com.example.data.api

import com.example.data.model.product.ProductsResponse
import com.example.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("products")
    suspend fun getProducts(
        @Query("id") id: String? = null,
        @Query("q") query: String? = null): ProductsResponse

}