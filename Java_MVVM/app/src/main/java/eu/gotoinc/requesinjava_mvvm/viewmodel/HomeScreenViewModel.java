package eu.gotoinc.requesinjava_mvvm.viewmodel;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.paging.PagedList;
import eu.gotoinc.requesinjava_mvvm.adapter.UserDataSourceFactory;
import eu.gotoinc.requesinjava_mvvm.adapter.UserPagingAdapter;
import eu.gotoinc.requesinjava_mvvm.util.architecture.ObservableViewModel;
import eu.gotoinc.requesinjava_mvvm.util.architecture.SingleLiveEvent;
import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import eu.gotoinc.requesinjava_mvvm.util.MainThreadExecutor;
import eu.gotoinc.requesinjava_mvvm.util.OnRecyclerItemClickListener;
import eu.gotoinc.requesinjava_mvvm.util.UserDiffUtilCallback;

public class HomeScreenViewModel extends ObservableViewModel implements OnRecyclerItemClickListener<UserResponse> {
	private ApiContract api;
	private SingleLiveEvent<UserResponse> liveUser;
	private SingleLiveEvent<Integer> liveErrorEvent;
	private UserPagingAdapter adapter;
	private PagedList<UserResponse> pagedList;


	public HomeScreenViewModel(@NonNull ApiContract api) {
		this.api = api;
		this.adapter = new UserPagingAdapter(new UserDiffUtilCallback(), this);
		this.liveUser = new SingleLiveEvent<>();
		this.liveErrorEvent = new SingleLiveEvent<>();
		init();
	}

	private void init() {
		PagedList.Config config = new PagedList.Config.Builder()
				.setEnablePlaceholders(false)
				.setPageSize(4)
				.build();
		pagedList = new PagedList.Builder<>(new UserDataSourceFactory(api, liveErrorEvent).create(), config)
				.setNotifyExecutor(new MainThreadExecutor())
				.setFetchExecutor(Executors.newSingleThreadExecutor())
				.build();
		adapter.submitList(pagedList);
	}

	@Bindable
	public UserPagingAdapter getAdapter() {
		return adapter;
	}

	public SingleLiveEvent<UserResponse> getLiveUser() {
		return liveUser;
	}

	public SingleLiveEvent<Integer> getLiveErrorEvent() {
		return liveErrorEvent;
	}

	@Override
	public void onItemClick(UserResponse userResponse) {
		liveUser.setValue(userResponse);
	}
}
