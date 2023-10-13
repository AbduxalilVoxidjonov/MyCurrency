package com.example.currency.Main

import com.example.currency.Data.ConverterApi
import com.example.currency.Data.Models.ExchangeResponse
import com.example.currency.Utils.Resource
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val api: ConverterApi
) : MainRepository {
    override suspend fun convertRate(
        from: String,
        to: String,
        amount: String
    ): Resource<ExchangeResponse> {
        return try {
            val response = api.convertRate(to, from, amount)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}