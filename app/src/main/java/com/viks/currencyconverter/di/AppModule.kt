package com.viks.currencyconverter.di

import android.content.Context
import com.viks.currencyconverter.data.local.CurrencyListDao
import com.viks.currencyconverter.data.local.LiveExchangeDao
import com.viks.currencyconverter.data.pref.MyPreferences
import com.viks.currencyconverter.data.remote.ApiService
import com.viks.currencyconverter.repository.DefaultMainRepository
import com.viks.currencyconverter.repository.MainRepository
import com.viks.currencyconverter.ui.adapter.CurrencyAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        api: ApiService,
        currencyListDao: CurrencyListDao,
        liveExchangeDao: LiveExchangeDao,
        pref: MyPreferences
    ): MainRepository =
        DefaultMainRepository(api, currencyListDao, liveExchangeDao, pref)

    @Provides
    fun provideCurrencyAdapter(): CurrencyAdapter = CurrencyAdapter()

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context) = MyPreferences(context = context)
}