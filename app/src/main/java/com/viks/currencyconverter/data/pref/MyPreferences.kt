package com.viks.currencyconverter.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.viks.currencyconverter.utils.Config.PREF_KEY_TS
import com.viks.currencyconverter.utils.Config.PREF_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * JetPack DataStore/preferencesDataStore is using to store timestamp (Long)
 */

class MyPreferences(
    context: Context
) {
    private val context = context.applicationContext
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
    private val timeStamp = longPreferencesKey(PREF_KEY_TS)

    val getLastTime: Flow<Long> = context.dataStore.data.map { it[timeStamp] ?: 0 }

    suspend fun setLastTime(ts: Long) {
        context.dataStore.edit { it[timeStamp] = ts }
    }
}