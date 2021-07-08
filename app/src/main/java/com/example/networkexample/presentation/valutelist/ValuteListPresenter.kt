package com.example.networkexample.presentation.valutelist

import android.net.ConnectivityManager
import android.util.Log
import com.example.networkexample.domain.model.Valute
import com.example.networkexample.domain.usecase.ClearValutesDbUseCase
import com.example.networkexample.domain.usecase.GetValutesFromDbUseCase
import com.example.networkexample.domain.usecase.GetValutesUseCase
import com.example.networkexample.domain.usecase.InsertValutesToDbUseCase

class ValuteListPresenter constructor(private val view: ValuteListView) {

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
                    Log.i("Log", "Перезапись в базу данных")
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
                    Log.i("Log", "Получение элементов из базы данных")
                },
                {
                    it.printStackTrace()
                    view.setProgressVisible(false)
                }
            )
    }


}