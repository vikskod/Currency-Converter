package com.viks.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.viks.currencyconverter.data.local.AppDatabase
import com.viks.currencyconverter.data.local.CurrencyListDao
import com.viks.currencyconverter.data.local.LiveExchangeDao
import com.viks.currencyconverter.utils.Config.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideLiveExchangeDao(appDatabase: AppDatabase): LiveExchangeDao {
        return appDatabase.liveExchangeDao()
    }

    @Singleton
    @Provides
    fun provideCurrencyListDao(appDatabase: AppDatabase): CurrencyListDao {
        return appDatabase.currencyListDao()
    }

}