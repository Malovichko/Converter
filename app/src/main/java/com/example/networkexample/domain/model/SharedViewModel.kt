package com.example.networkexample.domain.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val startCharCode = MutableLiveData<Valute>()
    val endCharCode = MutableLiveData<Valute>()

    fun sendStartCharCode(text: Valute) {
        startCharCode.value = text
    }

    fun sendEndCharCode(text: Valute) {
        endCharCode.value = text
    }
}