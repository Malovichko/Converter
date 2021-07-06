package com.example.networkexample.data

import retrofit2.Retrofit

class NetworkServiceHolder {

    companion object {
        var retrofit: Retrofit? = null
        set(value) {
            retrofitService = value!!.create(CBRValutesApiService::class.java)
            field = value
        }

        var retrofitService: CBRValutesApiService? = null
    }
}