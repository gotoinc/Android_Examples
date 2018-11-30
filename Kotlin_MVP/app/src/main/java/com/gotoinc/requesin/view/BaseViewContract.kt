package com.gotoinc.requesin.view

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
interface BaseViewContract {
    fun onError()
    fun onError(message: String)
    fun onTimeout()
    fun onNetworkError()
}