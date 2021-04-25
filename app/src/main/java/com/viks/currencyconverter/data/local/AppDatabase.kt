package com.viks.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.viks.currencyconverter.data.local.convertor.CurrencyListConverter
import com.viks.currencyconverter.data.local.convertor.LiveExchangeConverter
import com.viks.currencyconverter.data.model.CurrencyListResponse
import com.viks.currencyconverter.data.model.LiveExchangeResponse
import com.viks.currencyconverter.utils.Config.DB_VERSION

@Database(
    entities = [LiveExchangeResponse::class, CurrencyListResponse::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(CurrencyListConverter::class, LiveExchangeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun liveExchangeDao(): LiveExchangeDao
    abstract fun currencyListDao(): CurrencyListDao
}