package com.example.routeandroidtask.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Product
import com.example.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<Product?>?>()
    val productsLiveData = _productsLiveData

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String?>()

    private val _searchTextLiveData = MutableLiveData<String>()
    val searchTextLiveData: LiveData<String> get() = _searchTextLiveData


    fun getProducts(){
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.IO){
            val result = getProductsUseCase.invokeProductList()
            loadingLiveData.postValue(false)
            when(result) {
                is ResultWrapper.Loading -> {
                }
                is ResultWrapper.Success -> {
                    productsLiveData.postValue(result.data)
                }
                is ResultWrapper.Error -> {
                    errorLiveData.postValue(result.message)
                }

                else -> {}
            }

        }
    }

    fun getSearchedProduct(query: String){
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.IO){
            val result = getProductsUseCase.invokeSearchedProduct(query = query)
            loadingLiveData.postValue(false)
            when(result){
                is ResultWrapper.Loading -> {

                }
                is ResultWrapper.Success -> {
                    getSearchedList(query, result.data)
                }
                is ResultWrapper.Error -> {
                    errorLiveData.postValue(result.message)
                }

                else -> {}
            }
        }
    }

    private fun getSearchedList(query: String, productList: List<Product?>?){
        val searchedList = productList?.filter { product ->
                query.let {
                    product?.title?.lowercase()?.contains(query.lowercase())
                } == true ||
                        query.let {
                            product?.description?.lowercase()?.contains(query.lowercase())
                        } == true
            }
        productsLiveData.postValue(searchedList)
    }

    fun setSearchText(searchText: String) {
        _searchTextLiveData.value = searchText
    }
}