package com.example.networkexample.domain

import com.example.networkexample.domain.model.Valute

sealed class Result
data class SuccessResult(val value: Valute): Result()
data class ExceptionResult(val exception: Exception): Result()