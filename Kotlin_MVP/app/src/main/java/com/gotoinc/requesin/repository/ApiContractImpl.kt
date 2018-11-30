package com.gotoinc.requesin.repository

import com.gotoinc.requesin.repository.datamodel.PaginationResponse
import com.gotoinc.requesin.repository.datamodel.UserResponse
import com.gotoinc.requesin.view.BaseViewContract
import com.gotoinc.requesin.view.home.HomeContract
import com.gotoinc.requesin.view.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
class ApiContractImpl(private val queries: RetrofitRequest) : ApiContract {
    override fun getUsersList(page: Int, view: HomeContract, baseView: BaseViewContract) {
        queries.getUserList(page)
            .enqueue(object: Callback<PaginationResponse<List<UserResponse>>> {
                override fun onResponse(
                    call: Call<PaginationResponse<List<UserResponse>>>,
                    response: Response<PaginationResponse<List<UserResponse>>>
                ) {
                    val paginationResponse= response.body()
                    paginationResponse?.let {
                        view.setUsers(User.from(paginationResponse.data))
                        val nextPage = paginationResponse.page + 1
                        if (paginationResponse.totalPages >= nextPage)
                            view.nextPage(true, nextPage)
                        else
                            view.nextPage(false, paginationResponse.page)
                    }
                }

                override fun onFailure(call: Call<PaginationResponse<List<UserResponse>>>, t: Throwable) {
                    when (t) {
                        is HttpException -> baseView.onError()
                        is SocketTimeoutException -> baseView.onTimeout()
                        is IOException -> baseView.onNetworkError()
                        else -> baseView.onError(t.localizedMessage)
                }
            }
        })
    }

}