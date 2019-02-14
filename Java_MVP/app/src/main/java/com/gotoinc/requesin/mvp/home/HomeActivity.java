package com.gotoinc.requesin.mvp.home;

import android.os.Bundle;

import com.gotoinc.requesin.R;
import com.gotoinc.requesin.mvp.common.data_model.User;
import com.gotoinc.requesin.mvp.detail.DetailFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public final class HomeActivity extends AppCompatActivity implements HomeNavigation {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(savedInstanceState == null)
            openHomeFragment();
    }

    @Override
    public void openHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, HomeFragment.getInstance(), HomeFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void openDetailsFragment(@NonNull User user) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DetailFragment.getInstance(user), DetailFragment.class.getSimpleName())
                .addToBackStack(HomeFragment.class.getSimpleName())
                .commit();
    }
}
