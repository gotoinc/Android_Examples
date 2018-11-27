package com.gotoinc.requesin.view.home;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.gotoinc.requesin.R;
import com.gotoinc.requesin.view.BaseViewContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity implements BaseViewContract {
    private ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        showFirstFragment();
    }

    private void findViews() {
        root = findViewById(R.id.root);
    }

    private void showFirstFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, HomeFragment.getInstance(), HomeFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onError() {
        Snackbar.make(root, R.string.error_unknown, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeout() {
        Snackbar.make(root, R.string.error_unknown, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError() {
        Snackbar.make(root, R.string.error_unknown, Snackbar.LENGTH_SHORT).show();
    }
}
