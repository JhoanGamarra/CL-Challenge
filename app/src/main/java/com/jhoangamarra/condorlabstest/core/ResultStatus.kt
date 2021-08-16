package com.jhoangamarra.condorlabstest.core

sealed class ResultStatus<out T> {

    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Failure(val exception: Exception) : ResultStatus<Nothing>()

}