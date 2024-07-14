package com.example.data.repository.products

import com.example.data.dataSourceContract.ProductsDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.repositories.ProductsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {
    private lateinit var productsRepository: ProductsRepository
    private val productDataSource = mockk<ProductsDataSource>()

    @Before
    fun setUp() {
        productsRepository = ProductsRepositoryImpl(productDataSource)
    }

    @Test
    fun `when call getProduct it should call productDataSource`() = runTest{
        val response = ResultWrapper.Success<List<Product?>?>(
            data = listOf(
                Product(),
                Product(),
                Product()
            )
        )
        coEvery { productDataSource.getProducts() } returns response

        val result = productsRepository.getProducts() as ResultWrapper.Success<List<Product?>?>
        coEvery { productDataSource.getProducts() }
        assertEquals(3, result.data?.size)

    }
}