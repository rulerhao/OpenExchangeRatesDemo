package com.rulhouse.openexchangeratesdemo.remote.rates.dto.error_body

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class ErrorBodyParser {

    fun <T: Any>parse(response: Response<T>): RatesApiErrorBody? {
        val gson = Gson()
        val type = object : TypeToken<RatesApiErrorBody>() {}.type

        return gson.fromJson(response.errorBody()!!.charStream(), type)
    }

}