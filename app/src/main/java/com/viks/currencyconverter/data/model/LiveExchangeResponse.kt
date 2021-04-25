package com.viks.currencyconverter.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_live_exchange")
data class LiveExchangeResponse(
    // No need of multiple data row for this app
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("quotes")
    val quotes: Map<String, Double>?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("terms")
    val terms: String?,
    @SerializedName("timestamp")
    val timestamp: Int?
)