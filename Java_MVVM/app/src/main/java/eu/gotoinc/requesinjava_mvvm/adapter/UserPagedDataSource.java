package eu.gotoinc.requesinjava_mvvm.adapter;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import eu.gotoinc.requesinjava_mvvm.R;
import eu.gotoinc.requesinjava_mvvm.util.architecture.SingleLiveEvent;
import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import io.reactivex.schedulers.Schedulers;

public class UserPagedDataSource extends PageKeyedDataSource<Integer, UserResponse> {
	private ApiContract api;
	private SingleLiveEvent<Integer> liveErrorEvent;

	public UserPagedDataSource(ApiContract api) {
		this.api = api;
	}


	public UserPagedDataSource(ApiContract api, SingleLiveEvent<Integer> liveErrorEvent) {
		this.api = api;
		this.liveErrorEvent = liveErrorEvent;
	}

	@Override
	public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
		api.getUsersList(0)
				.subscribeOn(Schedulers.io())
				.subscribe(
						listPaginationResponse ->
								callback.onResult(listPaginationResponse.getData(), null, listPaginationResponse.getPage() + 1),
						throwable -> liveErrorEvent.setValue(R.string.error_message)
				);
	}


	@Override
	public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

	}

	@Override
	public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
		api.getUsersList((Integer) params.key)
				.subscribeOn(Schedulers.io())
				.subscribe(
						listPaginationResponse -> {
							boolean hasNext = listPaginationResponse.getTotalPages() > listPaginationResponse.getPage();
							callback.onResult(listPaginationResponse.getData(), hasNext ? listPaginationResponse.getPage() + 1 : null);
						}, throwable -> liveErrorEvent.setValue(R.string.error_message)
				);
	}
}
