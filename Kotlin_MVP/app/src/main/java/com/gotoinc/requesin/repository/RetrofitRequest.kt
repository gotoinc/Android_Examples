package com.gotoinc.requesin.repository

import com.gotoinc.requesin.repository.datamodel.PaginationResponse
import com.gotoinc.requesin.repository.datamodel.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
const val HOSTNAME = "https://reqres.in/"
const val API_VERSION = "api"
const val LIST_OF_USERS = "/users"

interface RetrofitRequest {
    @GET("$API_VERSION$LIST_OF_USERS")
    fun getUserList(@Query("page") page: Int): Call<PaginationResponse<List<UserResponse>>>
}