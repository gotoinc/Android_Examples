package eu.gotoinc.requesinjava_mvvm.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import eu.gotoinc.requesinjava_mvvm.R;
import eu.gotoinc.requesinjava_mvvm.util.architecture.Factory;
import eu.gotoinc.requesinjava_mvvm.databinding.FragmentUserDetailBinding;
import eu.gotoinc.requesinjava_mvvm.di.DIContainerImpl;
import eu.gotoinc.requesinjava_mvvm.viewmodel.HomeScreenViewModel;

import static eu.gotoinc.requesinjava_mvvm.util.AppConstant.HOME_VM;


public class InfoFragment extends Fragment {
	private FragmentUserDetailBinding binding;

	public InfoFragment() {
		// Required empty public constructor
	}

	public static InfoFragment newInstance() {
		return new InfoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false);
		binding.setVm(ViewModelProviders.of(getActivity(), new Factory(DIContainerImpl.getInstance().getQueries()))
				.get(HOME_VM, HomeScreenViewModel.class));
		return binding.getRoot();
	}

}
