package eu.gotoinc.requesinjava_mvvm;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import eu.gotoinc.requesinjava_mvvm.di.DIContainerImpl;
import eu.gotoinc.requesinjava_mvvm.fragment.HomeFragment;
import eu.gotoinc.requesinjava_mvvm.fragment.InfoFragment;
import eu.gotoinc.requesinjava_mvvm.util.architecture.Factory;
import eu.gotoinc.requesinjava_mvvm.viewmodel.HomeScreenViewModel;

import static eu.gotoinc.requesinjava_mvvm.util.AppConstant.HOME_VM;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		HomeScreenViewModel viewModel = ViewModelProviders.of(this, new Factory(DIContainerImpl.getInstance().getQueries()))
				.get(HOME_VM, HomeScreenViewModel.class);

		showFragment(HomeFragment.newInstance(), false);

		viewModel.getLiveErrorEvent().observe(this, resId ->
				Snackbar.make(findViewById(R.id.container), resId, Snackbar.LENGTH_LONG).show());

		viewModel.getLiveUser().observe(this, userResponse -> {
			if (userResponse != null) {
				showFragment(InfoFragment.newInstance(), true);
			}
		});
	}

	private void showFragment(Fragment fragment, boolean isBackstackEnabled) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.container, fragment);
		if (isBackstackEnabled) fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}
