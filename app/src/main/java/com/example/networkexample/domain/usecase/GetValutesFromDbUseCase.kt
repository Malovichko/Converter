package com.example.networkexample.domain.usecase

import com.example.networkexample.data.database.DataBaseHolder
import com.example.networkexample.domain.mappers.SimpleValuteMapper
import com.example.networkexample.domain.model.Valute
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetValutesFromDbUseCase {
    fun execute(): Single<List<Valute>> = DataBaseHolder
        .database!!
        .valuteListDao()
        .getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            it.map { SimpleValuteMapper.mapDbToDomain(it) }.toList()
        }
}