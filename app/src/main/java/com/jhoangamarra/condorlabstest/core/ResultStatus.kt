package com.jhoangamarra.condorlabstest.core

sealed class ResultStatus<out T> {

    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Failure(val exception: Exception) : ResultStatus<Nothing>() {
        override fun equals(other: Any?): Boolean {
            return other is Failure && other.exception.message == exception.message && exception.cause == other.exception.cause && exception.javaClass == other.exception.javaClass
        }

        override fun hashCode(): Int {
            return exception.hashCode()
        }
    }

}