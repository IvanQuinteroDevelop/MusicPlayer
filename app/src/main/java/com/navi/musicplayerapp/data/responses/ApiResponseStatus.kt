package com.navi.musicplayerapp.data.responses

sealed class ApiResponseStatus<out T> {
    class Loading<T>: ApiResponseStatus<T>()
    class Success<out T>(val data: T): ApiResponseStatus<T>()
    class Error<T>(val messageId: Int): ApiResponseStatus<T>()
}