package eu.gotoinc.requesinjava_mvvm.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import eu.gotoinc.requesinjava_mvvm.BR;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import eu.gotoinc.requesinjava_mvvm.util.OnRecyclerItemClickListener;

public class UserItemViewModel extends BaseObservable {
	private UserResponse user;
	private OnRecyclerItemClickListener<UserResponse> listener;

	public UserItemViewModel(UserResponse user) {
		this.user = user;
	}

	public UserItemViewModel(UserResponse user, OnRecyclerItemClickListener<UserResponse> listener) {
		this(user);
		this.listener = listener;
	}

	@Bindable
	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
		notifyPropertyChanged(BR.user);
	}

	public void onItemClick(){
		if (listener != null){
			listener.onItemClick(user);
		}
	}
}