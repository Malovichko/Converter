package com.example.networkexample.domain.usecase

import com.example.networkexample.data.NetworkServiceHolder
import com.example.networkexample.domain.mappers.SimpleValuteMapper
import com.example.networkexample.domain.model.Valute
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetValutesUseCase {
    fun execute(): Single<List<Valute>> = NetworkServiceHolder.retrofitService!!.getValuteList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            return@map it.valuteResponses?.map {
                SimpleValuteMapper.mapApiToDomain(it)
            }?.toList()
        }
}