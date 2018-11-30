package eu.gotoinc.kotlinmvvm.repository

import eu.gotoinc.kotlinmvvm.repository.datamodel.response.PaginationResponse
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import io.reactivex.Single

interface ApiContract {
    fun getUsersList(page: Int): Single<PaginationResponse<List<UserResponse>>>
}