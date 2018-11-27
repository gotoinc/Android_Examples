package eu.gotoinc.requesinjava_mvvm.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;

public class UserDiffUtilCallback extends DiffUtil.ItemCallback<UserResponse> {

	@Override
	public boolean areItemsTheSame(@NonNull UserResponse oldItem, @NonNull UserResponse newItem) {
		return oldItem.equals(newItem);
	}

	@Override
	public boolean areContentsTheSame(@NonNull UserResponse oldItem, @NonNull UserResponse newItem) {
		return oldItem.getAvatar().equals(newItem.getAvatar()) && oldItem.getLastName().equals(newItem.getLastName()) && oldItem.getFirstName().equals(newItem.getFirstName()) ;
	}
}

