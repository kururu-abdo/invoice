package com.eamar.invoice.src.core

sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<out T>(
        val data: T?=null
    ): Response<T>()

    data class Failure(
        val e: Exception?
    ): Response<Nothing>()
}