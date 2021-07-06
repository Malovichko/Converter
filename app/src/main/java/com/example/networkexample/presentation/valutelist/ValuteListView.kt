package com.example.networkexample.presentation.valutelist

import com.example.networkexample.domain.model.Valute

interface ValuteListView {

    fun setupItemList(list: List<Valute>)

    fun setProgressVisible(isVisible: Boolean)
}