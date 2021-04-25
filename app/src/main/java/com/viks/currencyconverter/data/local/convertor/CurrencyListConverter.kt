package com.viks.currencyconverter.data.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyListConverter {

    @TypeConverter
    fun toMapData(value: String?): Map<String, String> {
        return Gson().fromJson(
            value,
            object : TypeToken<Map<String, String>?>() {}.type
        )
    }

    @TypeConverter
    fun fromMapData(data: Map<String, String>?): String {
        return Gson().toJson(data)
    }
}