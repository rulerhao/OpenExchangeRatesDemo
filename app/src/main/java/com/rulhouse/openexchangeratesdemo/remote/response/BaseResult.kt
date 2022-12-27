package com.rulhouse.openexchangeratesdemo.remote.response

sealed class BaseResult <out T: Any, out U: Any> {
    data class Success <T: Any>(val data: T): BaseResult<T, Nothing>()
    data class Error <U : Any>(val rawResponse: U): BaseResult<Nothing, U>()
}