package com.example.image.utils

public interface DataCallbackListener<T> {
    fun onSuccess(response:T)
    fun onError(errorCode: Int)
}