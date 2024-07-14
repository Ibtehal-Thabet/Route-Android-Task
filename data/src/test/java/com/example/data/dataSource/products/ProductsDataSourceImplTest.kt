package com.example.data.dataSource.products

import com.example.data.api.WebService
import com.example.data.dataSourceContract.ProductsDataSource
import com.example.data.model.product.ProductDto
import com.example.data.model.product.ProductsResponse
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductsDataSourceImplTest {
    lateinit var productsDataSource: ProductsDataSource
    private val webService = mockk<WebService>()

    @Before
    fun setUp() {
        productsDataSource = ProductsDataSourceImpl(webService)
    }

    @Test
    fun `when call getProduct from data source it should get data from api`() = runTest {
        val productList = listOf(
            ProductDto(),
            ProductDto(),
            ProductDto()
        )
        val productsResponse = ProductsResponse(products = productList)
        coEvery { webService.getProducts() } returns productsResponse

        val result = productsDataSource.getProducts() as ResultWrapper.Success<List<Product?>?>

        assertEquals(3, result.data?.size)
        coVerify(exactly = 1) { webService.getProducts() }

    }

}