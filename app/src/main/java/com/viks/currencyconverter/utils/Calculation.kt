package com.viks.currencyconverter.utils

/**
 * Kotlin class to calculate exchange rate between 2 different currency
 * with the help of USD.
 * Some api provide this functionality from the backend but api.currencylayer.com
 * is not providing on Free version API
 *
 * LOGIC:
 * If USDAUD = 1.3 and USDNRS = 120
 * Then
 * AUDNRS = USDNRS/USDAUD = 92.3
 */

class Calculation constructor(map: Map<String, Double>) {

    private val pairList = map.toList()
    private val pairList2: MutableList<Pair<String, Double>> = mutableListOf()

    fun getChangedData(from: String): Map<String, Double> {
        for (pair in pairList) {
            if (pair.first.substring(3, 6).contains(from)) {
                val fromValue = pair.second
                for (p in pairList) {
                    val p1: Pair<String, Double> = p.copy(second = p.second / fromValue)
                    pairList2.add(p1)
                }
                break
            }
        }
        return pairList2.toMap()
    }

    fun getChangedData(from: String, num:Double): Map<String, Double> {
        for (pair in pairList) {
            if (pair.first.substring(3, 6).contains(from)) {
                val fromValue = pair.second
                for (p in pairList) {
                    val p1: Pair<String, Double> = p.copy(second = p.second / fromValue * num)
                    pairList2.add(p1)
                }
                break
            }
        }
        return pairList2.toMap()
    }

    fun getChangedData(num: Double): Map<String, Double> {
        for (pair in pairList) {
                for (p in pairList) {
                    val p1: Pair<String, Double> = p.copy(second = p.second  * num)
                    pairList2.add(p1)
                }
                break
        }
        return pairList2.toMap()
    }
}