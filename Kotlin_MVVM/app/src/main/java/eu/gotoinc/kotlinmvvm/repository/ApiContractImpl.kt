package eu.gotoinc.kotlinmvvm.repository

import eu.gotoinc.kotlinmvvm.repository.datamodel.response.PaginationResponse
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import io.reactivex.Single

class ApiContractImpl(private val queries: RetrofitRequest) : ApiContract{

    override fun getUsersList(page: Int): Single<PaginationResponse<List<UserResponse>>> {
        return queries.getUsersList(page)
    }
}