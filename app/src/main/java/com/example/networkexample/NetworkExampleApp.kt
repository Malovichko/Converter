package com.example.networkexample

import android.app.Application
import com.example.networkexample.data.NetworkServiceHolder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class NetworkExampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initRetrofitService()
    }

    private fun initRetrofitService() {
        NetworkServiceHolder.retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}