package eu.gotoinc.requesinjava_mvvm.util.architecture;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;
import eu.gotoinc.requesinjava_mvvm.viewmodel.HomeScreenViewModel;

public class Factory extends ViewModelProvider.NewInstanceFactory {

	private ApiContract api;

	public Factory(ApiContract api) {
		super();
		this.api = api;
	}



	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		if (modelClass == HomeScreenViewModel.class) {
			return (T) new HomeScreenViewModel(api);
		}
		return null;
	}
}
