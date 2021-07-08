package com.example.networkexample.domain.usecase

import com.example.networkexample.data.database.DataBaseHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class ClearValutesDbUseCase {
    fun execute(): Completable = DataBaseHolder
        .database!!
        .valuteListDao()
        .clearAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}