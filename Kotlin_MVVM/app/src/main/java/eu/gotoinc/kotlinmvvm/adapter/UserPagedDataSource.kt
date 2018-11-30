package eu.gotoinc.kotlinmvvm.adapter

import androidx.paging.PageKeyedDataSource
import eu.gotoinc.kotlinmvvm.R
import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import eu.gotoinc.kotlinmvvm.util.architecture.SingleLiveEvent
import io.reactivex.schedulers.Schedulers

class UserPagedDataSource(private val api: ApiContract, private val liveErrorEvent: SingleLiveEvent<Int>) :
    PageKeyedDataSource<Int, UserResponse>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserResponse>) {
        api.getUsersList(0)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { listPaginationResponse ->
                    callback.onResult(listPaginationResponse.data,
                        null,
                        listPaginationResponse.page + 1)
                },
                { throwable -> liveErrorEvent.setValue(R.string.error_message) }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserResponse>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserResponse>) {
        api.getUsersList(params.key)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { listPaginationResponse ->
                    val hasNext = listPaginationResponse.totalPages > listPaginationResponse.page
                    callback.onResult(
                        listPaginationResponse.data,
                        if (hasNext) listPaginationResponse.page + 1 else null
                    )
                }, { throwable -> liveErrorEvent.setValue(R.string.error_message) }
            )
    }


}