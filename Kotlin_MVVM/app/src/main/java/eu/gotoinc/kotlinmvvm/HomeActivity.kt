package eu.gotoinc.kotlinmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import eu.gotoinc.kotlinmvvm.di.DIContainerImpl
import eu.gotoinc.kotlinmvvm.fragment.HomeFragment
import eu.gotoinc.kotlinmvvm.fragment.InfoFragment
import eu.gotoinc.kotlinmvvm.util.HOME_VM
import eu.gotoinc.kotlinmvvm.util.architecture.Factory
import eu.gotoinc.kotlinmvvm.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewModel = ViewModelProviders.of(this, Factory(DIContainerImpl.queries))
            .get(HOME_VM, HomeScreenViewModel::class.java)

        showFragment(HomeFragment.newInstance(), false)

        viewModel.liveErrorEvent.observe(this,
            Observer { resId -> Snackbar.make(container, resId, Snackbar.LENGTH_LONG).show() })

        viewModel.liveUser.observe(this,
            Observer { userResponse -> if (userResponse != null) showFragment(InfoFragment.newInstance(), true) })
    }

    private fun showFragment(fragment: Fragment, isBackStackEnabled: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment)
            if (isBackStackEnabled) addToBackStack(null)
            commit()
        }
    }


}
