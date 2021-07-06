package com.example.networkexample.domain.converter

import com.example.networkexample.domain.model.Valute


class Converter constructor(
    private var inputValute: Double, private var startValute: Valute,
    private var endValute: Valute
) {

    private fun changePointToDot(string: String): String {
        val firstPart = string.substringBefore(',')
        val lastPart = string.substringAfter(',')
        return "$firstPart.$lastPart"
    }

    fun convertFromStartValute(): Double {
        val baseA: Double = changePointToDot(startValute.value).toDouble() / startValute.nominal.toDouble()
        val baseB: Double = changePointToDot(endValute.value).toDouble() / endValute.nominal.toDouble()
        return baseA * inputValute / baseB

    }
}