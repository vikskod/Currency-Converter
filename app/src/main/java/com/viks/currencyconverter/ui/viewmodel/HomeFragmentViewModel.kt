package com.viks.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viks.currencyconverter.repository.DefaultMainRepository
import com.viks.currencyconverter.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: DefaultMainRepository,
) :
    ViewModel() {

    val currencyList = repository.getLocalCurrencyList()
    val liveExchange = repository.getLocalLiveExchange()

    fun checkAndFetchData() {
        // Fetch remote data if data is older than 30 minutes and save it on local db
        if ((System.currentTimeMillis() - getLastTimeStamp()) > 30 * 60 * 1000) {
            saveRemoteCurrencyList()
            saveRemoteLiveExchange()

            // Updating last updated timestamp on JetPack DataStore preference
            saveLastTimeStamp(System.currentTimeMillis())
        }
    }

    private fun saveRemoteCurrencyList() {
        viewModelScope.launch {
            val firstRequest = repository.getCurrencyList()
            if (firstRequest.status == Resource.Status.SUCCESS) {
                firstRequest.data?.let { repository.setLocalCurrencyList(it) }
            }
        }
    }

    private fun saveRemoteLiveExchange() {
        viewModelScope.launch {
            val secondRequest = repository.getLiveExchange()
            if (secondRequest.status == Resource.Status.SUCCESS) {
                secondRequest.data?.let { repository.setLocalLiveExchange(it) }
            }
        }
    }

    private fun saveLastTimeStamp(ts: Long) {
        viewModelScope.launch {
            repository.setLastTimeStamp(ts)
        }
    }

    private fun getLastTimeStamp(): Long {
        var ts: Long = 0
        viewModelScope.launch {
            repository.getLastTimeStamp().collect {
                ts = it
            }
        }
        return ts
    }
}