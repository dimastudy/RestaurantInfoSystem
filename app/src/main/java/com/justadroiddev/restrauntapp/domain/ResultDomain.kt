package com.justadroiddev.restrauntapp.domain

sealed class ResultDomain<T>{
    class Success<T>(val data: T) : ResultDomain<T>()
    class Error<T>(val message: String) : ResultDomain<T>()
}