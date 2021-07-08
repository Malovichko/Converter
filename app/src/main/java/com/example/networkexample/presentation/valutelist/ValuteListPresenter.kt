package com.example.networkexample.presentation.valutelist

import com.example.networkexample.data.NetworkServiceHolder
import com.example.networkexample.domain.mappers.SimpleValuteMapper
import com.example.networkexample.domain.model.Valute
import com.example.networkexample.domain.usecase.ClearValutesDbUseCase
import com.example.networkexample.domain.usecase.GetValutesFromDbUseCase
import com.example.networkexample.domain.usecase.GetValutesUseCase
import com.example.networkexample.domain.usecase.InsertValutesToDbUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ValuteListPresenter constructor(private val view: ValuteListView) {

    private val retrofitService = NetworkServiceHolder.retrofitService!!

    private val getValutesUseCase = GetValutesUseCase()
    private val clearValuteTableUseCase = ClearValutesDbUseCase()
    private val insertValutesToDbUseCase = InsertValutesToDbUseCase()
    private val getValutesFromDbUseCase = GetValutesFromDbUseCase()

    fun onViewCreated() {
        view.setProgressVisible(true)
        getValutesUseCase.execute()
            .subscribe(
                {
                    rewriteValutesTable(it)
                    view.setupItemList(it)
                    view.setProgressVisible(false)
                },
                {
                    it.printStackTrace()
                    view.setProgressVisible(false)
                }
            )
    }

    private fun rewriteValutesTable(valutes: List<Valute>) {
        clearValuteTableUseCase.execute()
            .andThen(insertValutesToDbUseCase.execute(valutes))
            .subscribe(
                {
                    println("Db table filled")
                },
                {
                    it.printStackTrace()
                }
            )
    }

    fun getValutesFromDb() {
        getValutesFromDbUseCase.execute()
            .subscribe(
                {
                    view.setupItemList(it)
                    view.setProgressVisible(false)
                },
                {
                    it.printStackTrace()
                    view.setProgressVisible(false)
                }
            )
    }

//    fun onViewCreated() {
//        view.setProgressVisible(true)
//        retrofitService.getValuteList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map {
//                return@map it.valuteResponses?.map {
//                    SimpleValuteMapper.mapApiToDomain(it)
//                }?.toList()
//            }
//            .subscribe(
//                {
//                    view.setupItemList(it!!)
//                    view.setProgressVisible(false)
//                },
//                {
//                    it.printStackTrace()
//                    view.setProgressVisible(false)
//                }
//            )
//
//
//    }
}