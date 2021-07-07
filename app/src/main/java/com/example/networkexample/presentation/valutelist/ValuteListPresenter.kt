package com.example.networkexample.presentation.valutelist

import com.example.networkexample.data.NetworkServiceHolder
import com.example.networkexample.domain.mappers.SimpleValuteMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ValuteListPresenter constructor(private val view: ValuteListView) {

    private val retrofitService = NetworkServiceHolder.retrofitService!!

    fun onViewCreated() {
        view.setProgressVisible(true)
        retrofitService.getValuteList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                return@map it.valuteResponses?.map {
                    SimpleValuteMapper.mapApiToDomain(it)
                }?.toList()
            }
            .subscribe(
                {
                    view.setupItemList(it!!)
                    view.setProgressVisible(false)
                },
                {
                    it.printStackTrace()
                    view.setProgressVisible(false)
                }
            )


    }
}