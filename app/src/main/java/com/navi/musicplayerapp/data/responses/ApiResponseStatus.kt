package com.navi.musicplayerapp.data.responses

sealed class ApiResponseStatus<T> {
    class Loading<T>: ApiResponseStatus<T>()
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Error<T>(val message: String): ApiResponseStatus<T>()
}