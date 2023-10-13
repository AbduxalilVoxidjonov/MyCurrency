package com.example.currency.Data.Models

data class ExchangeResponse(
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)