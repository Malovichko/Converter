package com.example.networkexample.data

import com.example.networkexample.data.model.ListResponseApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CBRValutesApiService {

    @GET("scripts/XML_daily.asp")
    fun getCharacters() : Single<ListResponseApiModel>
}