package eu.gotoinc.kotlinmvvm.viewmodel

import androidx.databinding.Bindable
import androidx.paging.PagedList
import eu.gotoinc.kotlinmvvm.adapter.UserDataSourceFactory
import eu.gotoinc.kotlinmvvm.adapter.UserPagingAdapter
import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import eu.gotoinc.kotlinmvvm.util.MainThreadExecutor
import eu.gotoinc.kotlinmvvm.util.UserDiffUtillCallback
import eu.gotoinc.kotlinmvvm.util.architecture.ObservableViewModel
import eu.gotoinc.kotlinmvvm.util.architecture.SingleLiveEvent
import java.util.concurrent.Executors

class HomeScreenViewModel(val api: ApiContract) : ObservableViewModel() {
    val liveUser: SingleLiveEvent<UserResponse> = SingleLiveEvent()
    val liveErrorEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    @Bindable val adapter: UserPagingAdapter
    private var pagedList: PagedList<UserResponse>

    init {
        adapter = UserPagingAdapter(UserDiffUtillCallback(), ::onClick)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(3)
            .build()
        pagedList = PagedList.Builder(UserDataSourceFactory(api, liveErrorEvent).create(), config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

        adapter.submitList(pagedList)
    }


    fun onClick(user: UserResponse?){
        liveUser.value = user
    }

}