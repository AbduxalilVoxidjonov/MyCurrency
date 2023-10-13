package com.example.currency.Data

import com.example.currency.Data.Models.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConverterApi {

    @Headers("apikey: MZinFCsU1APQDadJCVtUmqKXrdMInbvP")
    @GET("convert")
    suspend fun convertRate(
        @Query("to") to:String,
        @Query("from") from:String,
        @Query("amount") amount:String
    ):Response<ExchangeResponse>

}