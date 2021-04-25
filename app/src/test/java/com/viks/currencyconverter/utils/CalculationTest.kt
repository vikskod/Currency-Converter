package com.viks.currencyconverter.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculationTest{

    @Test
    fun currencyConversion_To_AEDUSD_Success(){
        val map1 : MutableMap<String, Double> = mutableMapOf()
        map1["USDAED"] = 2.0
        map1["USDAFN"] = 6.0
        map1["USDALL"] = 9.0
        map1["USDUSD"] = 1.0

        val result1 = Calculation(map1).getChangedData("AED")

        val map2 : MutableMap<String, Double> = mutableMapOf()
        map2["USDAED"] = 1.0
        map2["USDAFN"] = 3.0
        map2["USDALL"] = 4.5
        map2["USDUSD"] = 0.5

        assertThat(result1).isEqualTo(map2)
    }

    @Test
    fun currencyConversion_To_AFNAED_Success(){
        val map1 : MutableMap<String, Double> = mutableMapOf()
        map1["USDAED"] = 3.0
        map1["USDAFN"] = 6.0
        map1["USDALL"] = 9.0

        val result1 = Calculation(map1).getChangedData("AFN")

        val map2 : MutableMap<String, Double> = mutableMapOf()
        map2["USDAED"] = 0.5
        map2["USDAFN"] = 1.0
        map2["USDALL"] = 1.5

        assertThat(result1).isEqualTo(map2)
    }
}