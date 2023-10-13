package com.example.currency.Main

import com.example.currency.Data.Models.ExchangeResponse


interface MainRepository {

    suspend fun convertRate(
        from:String,
        to:String,
        amount:String
    ): com.example.currency.Utils.Resource<ExchangeResponse>

}