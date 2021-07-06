package com.example.networkexample.domain.mappers

import com.example.networkexample.data.model.SimpleValuteApiModel
import com.example.networkexample.domain.model.Valute

class SimpleValuteMapper {

    companion object {

        fun mapApiToDomain(source: SimpleValuteApiModel) = Valute(
            source.numCode,
            source.charCode,
            source.nominal,
            source.name,
            source.value
        )
    }
}