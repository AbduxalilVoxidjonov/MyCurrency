package com.example.currency.Utils

import com.example.currency.Data.Models.ExchangeResponse


sealed class ConvertEvent {
    data class Success(val result: ExchangeResponse) : ConvertEvent()
    data class Error(val errorMessage: String?) : ConvertEvent()
    object Loading : ConvertEvent()
    object Empty : ConvertEvent()
}
