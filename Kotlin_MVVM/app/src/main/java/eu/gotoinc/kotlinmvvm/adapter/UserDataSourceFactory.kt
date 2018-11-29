package eu.gotoinc.kotlinmvvm.adapter

import androidx.paging.DataSource
import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import eu.gotoinc.kotlinmvvm.util.architecture.SingleLiveEvent

class UserDataSourceFactory(private val api: ApiContract, private val liveErrorEvent: SingleLiveEvent<Int>) :
    DataSource.Factory<Int, UserResponse>() {

    override fun create(): UserPagedDataSource = UserPagedDataSource(api, liveErrorEvent)

}