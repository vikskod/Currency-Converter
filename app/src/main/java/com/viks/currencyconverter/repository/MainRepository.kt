package com.viks.currencyconverter.repository

import com.viks.currencyconverter.data.model.CurrencyListResponse
import com.viks.currencyconverter.data.model.LiveExchangeResponse
import com.viks.currencyconverter.utils.Resource

interface MainRepository {

    suspend fun getCurrencyList(): Resource<CurrencyListResponse>

    suspend fun getLiveExchange(): Resource<LiveExchangeResponse>
}