package eu.gotoinc.requesinjava_mvvm.adapter;

import androidx.paging.DataSource;
import eu.gotoinc.requesinjava_mvvm.util.architecture.SingleLiveEvent;
import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;

public class UserDataSourceFactory extends DataSource.Factory<Integer, UserResponse> {

	private final ApiContract api;
	private final SingleLiveEvent<Integer> liveErrorEvent;

	public UserDataSourceFactory(ApiContract api, SingleLiveEvent<Integer> liveErrorEvent) {
		this.api = api;
		this.liveErrorEvent = liveErrorEvent;
	}

	@Override
	public UserPagedDataSource create() {
		return new UserPagedDataSource(api, liveErrorEvent);
	}
}