package com.viks.currencyconverter.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_currency_list")
data class CurrencyListResponse(
    // No need of multiple data row for this app
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,
    @SerializedName("currencies")
    val currencies: Map<String, String>?,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("terms")
    val terms: String?
)