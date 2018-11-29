package eu.gotoinc.kotlinmvvm.di

import eu.gotoinc.kotlinmvvm.repository.ApiContract

interface DIContainer {
    val queries: ApiContract
}