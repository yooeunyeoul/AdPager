package com.dongeul.adpager.model

open class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    object Uninitialized: Result<Nothing>()
}