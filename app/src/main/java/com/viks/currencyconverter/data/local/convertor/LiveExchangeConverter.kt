package com.viks.currencyconverter.data.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LiveExchangeConverter {

    @TypeConverter
    fun toMap(value: String?): Map<String, Double> {
        return Gson().fromJson(
            value,
            object : TypeToken<Map<String, Double>?>() {}.type
        )
    }

    @TypeConverter
    fun fromMap(data: Map<String, Double>?): String {
        return Gson().toJson(data)
    }
}