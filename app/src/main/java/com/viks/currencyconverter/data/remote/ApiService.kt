package com.viks.currencyconverter.data.remote

import com.viks.currencyconverter.BuildConfig
import com.viks.currencyconverter.data.model.CurrencyListResponse
import com.viks.currencyconverter.data.model.LiveExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * Get all supported currency list
     */
    @GET("list")
    suspend fun getCurrencyList(
        @Query("access_key") appId: String = BuildConfig.API_KEY,
    ): Response<CurrencyListResponse>

    /**
     * Get live USD to Other currency conversion rate
     * Other to other currency doesn't support in free API
     */
    @GET("live")
    suspend fun getLiveExchange(
        @Query("access_key") appId: String = BuildConfig.API_KEY,
    ): Response<LiveExchangeResponse>
}