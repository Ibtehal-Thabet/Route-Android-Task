package com.example.domain.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.repositories.ProductsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {

    private lateinit var productsRepository: ProductsRepository

    @Before
    fun setUp() {
        productsRepository = mockk()
    }

    @Test
    fun invokeProductList() = runTest{
        val response = ResultWrapper.Success<List<Product?>?>(data = listOf())
        coEvery { productsRepository.getProducts() } returns response

        val result = productsRepository.getProducts() as ResultWrapper.Success<List<Product?>?>
        coEvery { productsRepository.getProducts() }
        assertThat(response, `is` (result))

    }
}