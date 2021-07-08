package com.example.networkexample.presentation.valutelist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
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