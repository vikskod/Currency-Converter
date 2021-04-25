package com.viks.currencyconverter.repository

import com.viks.currencyconverter.data.local.CurrencyListDao
import com.viks.currencyconverter.data.local.LiveExchangeDao
import com.viks.currencyconverter.data.model.CurrencyListResponse
import com.viks.currencyconverter.data.model.LiveExchangeResponse
import com.viks.currencyconverter.data.pref.MyPreferences
import com.viks.currencyconverter.data.remote.ApiService
import com.viks.currencyconverter.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: ApiService,
    private val currencyListDao: CurrencyListDao,
    private val liveExchangeDao: LiveExchangeDao,
    private val preferences: MyPreferences
) : MainRepository {
    override suspend fun getCurrencyList(): Resource<CurrencyListResponse> {
        return try {
            val response = api.getCurrencyList()
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.success(result)
            else Resource.error("Error", result)
        } catch (e: Exception) {
            Resource.error(e.printStackTrace().toString(), null)
        }
    }

    override suspend fun getLiveExchange(): Resource<LiveExchangeResponse> {
        return try {
            val response = api.getLiveExchange()
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.success(result)
            else Resource.error("Error", result)
        } catch (e: Exception) {
            Resource.error(e.printStackTrace().toString(), null)
        }
    }

    fun getLocalCurrencyList() = currencyListDao.getCurrencyList()

    fun getLocalLiveExchange() = liveExchangeDao.getLiveExchangeData()

    suspend fun setLocalCurrencyList(currencyListResponse: CurrencyListResponse) =
        currencyListDao.insertCurrencyList(currencyListResponse)

    suspend fun setLocalLiveExchange(liveExchangeResponse: LiveExchangeResponse) =
        liveExchangeDao.insertLiveExchange(liveExchangeResponse)

    suspend fun setLastTimeStamp(ts: Long) {
        preferences.setLastTime(ts)
    }

    fun getLastTimeStamp(): Flow<Long> = preferences.getLastTime
}