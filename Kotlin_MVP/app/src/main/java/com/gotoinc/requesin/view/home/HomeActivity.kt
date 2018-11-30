package com.gotoinc.requesin.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.gotoinc.requesin.R
import com.gotoinc.requesin.view.BaseViewContract
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BaseViewContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        showFirstFragment()
    }

    private fun showFirstFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment.getInstance(), HomeFragment.TAG)
            .commit()
    }

    override fun onError() {
        Snackbar.make(root_layout, R.string.error_unknown, Snackbar.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Snackbar.make(root_layout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onTimeout() {
        Snackbar.make(root_layout, R.string.error_unknown, Snackbar.LENGTH_SHORT).show()
    }

    override fun onNetworkError() {
        Snackbar.make(root_layout, R.string.error_unknown, Snackbar.LENGTH_SHORT).show()
    }
}
