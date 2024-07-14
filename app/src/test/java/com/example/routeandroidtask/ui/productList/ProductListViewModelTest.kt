package com.example.routeandroidtask.ui.productList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.usecase.GetProductsUseCase
import com.example.routeandroidtask.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {

    lateinit var viewModel: ProductListViewModel
    val getProductsUseCase = mockk<GetProductsUseCase>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ProductListViewModel(getProductsUseCase)
    }

    @Test
    fun `check loading when call getProduct`() = runTest {
//        val productResponse = ResultWrapper.Loading
//
//        coEvery { getProductsUseCase.invokeProductList() } returns productResponse

        viewModel.getProducts()
        val value = viewModel.loadingLiveData.getOrAwaitValue()
        assertEquals(true, value)
    }

    @Test
    fun `when call getProduct with success response it should call success list`() = runTest {
        val productResponse = ResultWrapper.Success<List<Product?>?>(listOf(
            Product(),
            Product(),
            Product()
        ))

        coEvery { getProductsUseCase.invokeProductList() } returns productResponse

        viewModel.getProducts()
        val value = viewModel.productsLiveData.getOrAwaitValue()
        assertNotEquals(0, value?.size)
    }

    @Test
    fun `when call getProduct with error response it should call error`() = runTest {
        val productResponse = ResultWrapper.Error(message = "error")

        coEvery { getProductsUseCase.invokeProductList() } returns productResponse

        viewModel.getProducts()
        val value = viewModel.errorLiveData.getOrAwaitValue()
        assertEquals("error", value.message)
    }

    @Test
    fun `when call getSearchedProduct with success response it should call success searched list`() = runTest {
        val productResponse = ResultWrapper.Success<List<Product?>?>(listOf(
            Product(title = "apple"),
            Product(title = "kiwi"),
            Product(title = "mascara")
        ))

        coEvery { getProductsUseCase.invokeSearchedProduct("kiwi") } returns productResponse

        viewModel.getSearchedProduct("kiwi")
        val value = viewModel.productsLiveData.getOrAwaitValue()
        assertEquals("kiwi", value?.get(0)?.title)
    }
}