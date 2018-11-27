package com.gotoinc.requesin.view.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gotoinc.requesin.R;
import com.gotoinc.requesin.view._model.User;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public class DetailFragment extends Fragment {
    private static final String ARG_USER = "ARG_USER";

    private AppCompatImageView image;
    private AppCompatTextView txtFullname;

    public static DetailFragment getInstance(@NonNull User user) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_USER, user);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void findViews(View view) {
        image = view.findViewById(R.id.image);
        txtFullname = view.findViewById(R.id.txt_fullname);
    }

    private void initViews() {
        if(getArguments() != null) {
            if (getArguments().getParcelable(ARG_USER) != null) {
                User user = getArguments().getParcelable(ARG_USER);
                if(user != null) {
                    Picasso.get().load(user.getAvatar()).fit().centerCrop().into(image);
                    txtFullname.setText(user.getFullname());
                }
            }
        }
    }
}
