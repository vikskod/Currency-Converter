package com.viks.currencyconverter.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viks.currencyconverter.data.model.CurrencyListResponse
import com.viks.currencyconverter.data.model.LiveExchangeResponse

@Dao
interface CurrencyListDao {

    // This function get all the data
    @Query("SELECT * FROM tbl_currency_list")
    fun getCurrencyList(): LiveData<List<CurrencyListResponse>>

    // This function insert or update data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyList(live: CurrencyListResponse)
}