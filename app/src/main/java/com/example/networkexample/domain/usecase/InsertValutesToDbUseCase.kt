package com.example.networkexample.domain.usecase

import com.example.networkexample.data.database.DataBaseHolder
import com.example.networkexample.domain.mappers.SimpleValuteMapper
import com.example.networkexample.domain.model.Valute
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class InsertValutesToDbUseCase {
    fun execute(valute: List<Valute>) = DataBaseHolder
        .database!!
        .valuteListDao()
        .insertAll(valute.map { SimpleValuteMapper.mapDomainToDb(it) }.toList())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}