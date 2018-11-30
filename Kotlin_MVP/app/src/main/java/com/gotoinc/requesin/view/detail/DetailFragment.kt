package com.gotoinc.requesin.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gotoinc.requesin.R
import com.gotoinc.requesin.view.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_detail.view.*

/**
 * Created by Illia Derevianko on 29.11.18.
 * GoTo Inc.
 */
class DetailFragment : Fragment() {
    companion object {
        const val TAG = "DetailFragment"
        const val ARG_USER = "ARG_USER"

        fun getInstance(user: User): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        val user = arguments?.getParcelable<User>(ARG_USER)
        if(user != null) {
            Picasso.get().load(user.avatar).fit().centerCrop().into(view.image)
            view.txt_fullname.text = user.fullName
        }
    }
}