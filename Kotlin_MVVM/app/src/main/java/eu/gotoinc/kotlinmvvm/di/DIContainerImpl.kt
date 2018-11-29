package eu.gotoinc.kotlinmvvm.di

import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.repository.ApiContractImpl
import eu.gotoinc.kotlinmvvm.repository.init.RetrofitInitImpl

object DIContainerImpl : DIContainer {
    override val queries: ApiContract

    init {
        this.queries = ApiContractImpl(RetrofitInitImpl.retrofitQueries)
    }
}