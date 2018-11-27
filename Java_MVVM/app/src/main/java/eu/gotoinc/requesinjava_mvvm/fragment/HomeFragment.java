package eu.gotoinc.requesinjava_mvvm.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import eu.gotoinc.requesinjava_mvvm.R;
import eu.gotoinc.requesinjava_mvvm.util.architecture.Factory;
import eu.gotoinc.requesinjava_mvvm.di.DIContainerImpl;
import eu.gotoinc.requesinjava_mvvm.viewmodel.HomeScreenViewModel;
import eu.gotoinc.requesinjava_mvvm.databinding.FragmentHomeBinding;

import static eu.gotoinc.requesinjava_mvvm.util.AppConstant.HOME_VM;

public class HomeFragment extends Fragment {
	private FragmentHomeBinding binding;

	public HomeFragment() {
		// Required empty public constructor
	}


	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
		binding.setVm(ViewModelProviders.of(getActivity(), new Factory(DIContainerImpl.getInstance().getQueries()))
				.get(HOME_VM, HomeScreenViewModel.class));
		binding.rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));
		return binding.getRoot();
	}

}
