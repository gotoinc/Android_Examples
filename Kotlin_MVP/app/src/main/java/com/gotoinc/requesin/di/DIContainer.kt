package com.gotoinc.requesin.di

import com.gotoinc.requesin.repository.ApiContract
import com.gotoinc.requesin.repository.ApiContractImpl
import com.gotoinc.requesin.repository.init.RetrofitInit
import com.gotoinc.requesin.repository.init.RetrofitInitImpl

/**
 * Created by Illia Derevianko on 29.11.18.
 * GoTo Inc.
 */
interface DIContainer {
    val queries: ApiContract
}

object DIContainerImpl : DIContainer {
    private val retrofitInit: RetrofitInit = RetrofitInitImpl()
    override val queries: ApiContract = ApiContractImpl(retrofitInit.requests)
}