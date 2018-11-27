package eu.gotoinc.requesinjava_mvvm.adapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import eu.gotoinc.requesinjava_mvvm.BR;

class ViewHolder extends RecyclerView.ViewHolder {
	private final ViewDataBinding binding;

	ViewHolder(ViewDataBinding binding) {
		super(binding.getRoot());
		this.binding = binding;
	}


	void bind(BaseObservable baseViewModel) {
		binding.setVariable(BR.vm, baseViewModel);
		binding.executePendingBindings();
	}

}