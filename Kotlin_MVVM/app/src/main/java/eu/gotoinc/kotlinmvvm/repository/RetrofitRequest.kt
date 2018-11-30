package eu.gotoinc.kotlinmvvm.repository

import eu.gotoinc.kotlinmvvm.repository.datamodel.response.PaginationResponse
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRequest {
    @GET(API_VERSION + LIST_OF_USERS)
    fun getUsersList(@Query("page") page: Int): Single<PaginationResponse<List<UserResponse>>>
}
