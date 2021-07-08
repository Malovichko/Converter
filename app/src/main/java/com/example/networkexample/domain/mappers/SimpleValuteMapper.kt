package com.example.networkexample.domain.mappers

import com.example.networkexample.data.database.entities.SimpleValuteEntity
import com.example.networkexample.data.model.SimpleValuteApiModel
import com.example.networkexample.domain.model.Valute

object SimpleValuteMapper {

    fun mapApiToDomain(source: SimpleValuteApiModel) = Valute(
        source.numCode,
        source.charCode,
        source.nominal,
        source.name,
        source.value
    )

    fun mapDomainToDb(source: Valute) = SimpleValuteEntity(
        source.numCode,
        source.charCode,
        source.nominal,
        source.name,
        source.value
    )

    fun mapDbToDomain(source: SimpleValuteEntity) = Valute(
        source.numCode,
        source.charCode,
        source.nominal,
        source.name,
        source.value
    )
}