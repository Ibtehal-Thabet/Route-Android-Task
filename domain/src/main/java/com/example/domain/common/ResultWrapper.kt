package com.example.domain.common

import java.lang.Exception

sealed class ResultWrapper<out T> {

    object Loading : ResultWrapper<Nothing>()

    data class Success<T>(val data : T, val message: String? = null) : ResultWrapper<T>()

    data class Error(val message: String?, val statusCode: Int? = null):ResultWrapper<Nothing>()
}