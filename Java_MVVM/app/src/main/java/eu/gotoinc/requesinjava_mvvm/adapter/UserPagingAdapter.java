package eu.gotoinc.requesinjava_mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import eu.gotoinc.requesinjava_mvvm.R;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import eu.gotoinc.requesinjava_mvvm.util.OnRecyclerItemClickListener;
import eu.gotoinc.requesinjava_mvvm.viewmodel.UserItemViewModel;

public class UserPagingAdapter extends PagedListAdapter<UserResponse, ViewHolder> {
	private OnRecyclerItemClickListener<UserResponse> listener;

	public UserPagingAdapter(@NonNull DiffUtil.ItemCallback<UserResponse> diffCallback) {
		super(diffCallback);
	}

	public UserPagingAdapter(@NonNull DiffUtil.ItemCallback<UserResponse> diffCallback, OnRecyclerItemClickListener<UserResponse> listener) {
		super(diffCallback);
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list, parent, false);
		return new ViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.bind(new UserItemViewModel(getItem(position), listener));
	}

}
