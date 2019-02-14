package com.gotoinc.requesin.mvp.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.gotoinc.requesin.R;
import com.gotoinc.requesin.di.PresentersContainerImpl;
import com.gotoinc.requesin.mvp.common.adapter.UsersAdapter;
import com.gotoinc.requesin.mvp.common.adapter.diff_util.UsersDiffUtilCallback;
import com.gotoinc.requesin.mvp.common.data_model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public final class HomeFragment extends Fragment implements HomeContract.View {
    private FrameLayout root;
    private RecyclerView rvUsers;
    private UsersAdapter adapter;

    private List<User> adapterData;

    private HomeContract.Presenter presenter;

    private HomeNavigation homeNavigation;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeNavigation = (HomeNavigation) getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PresentersContainerImpl.getInstance().of(HomePresenter.class.getSimpleName(), HomePresenter.class);
        presenter.attachView(this);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);

        initList();
        presenter.load(savedInstanceState);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        presenter.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        super.onDetach();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onDestroy() {
        if(!getActivity().isChangingConfigurations()) {
            presenter.destroy();
            PresentersContainerImpl.getInstance().release(HomePresenter.class.getSimpleName());
        }
        super.onDestroy();
    }

    private void findViews(View view) {
        root = view.findViewById(R.id.root);
        rvUsers = view.findViewById(R.id.rv_users);
    }

    private void initList() {
        adapter = new UsersAdapter(getContext(), user -> homeNavigation.openDetailsFragment(user));
        rvUsers.setAdapter(adapter);

        rvUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisiblePosition;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (recyclerView.getLayoutManager() != null)
                        lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    if (adapter.getItemCount() - 1 - lastVisiblePosition <= 2) {
                        presenter.load();
                    }
                }
            }
        });
    }

    @Override
    public void drawState(@NonNull HomeContract.Model model) {
        if(model.isError() && model.isErrorShowing())
            Snackbar.make(root, model.getErrorMessage(), Snackbar.LENGTH_SHORT).addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    presenter.dismissErrorView();
                }
            }).show();
        else {
            if(model.isUsersLoaded()) {
                if (adapterData == null) {
                    adapterData = new ArrayList<>(model.getUsers());
                    adapter.setData(adapterData);
                    adapter.notifyItemRangeInserted(0, adapterData.size());
                } else {
                    model.getUsers().addAll(0, adapterData);
                    UsersDiffUtilCallback usersDiffUtilCallback = new UsersDiffUtilCallback(adapterData, model.getUsers());
                    DiffUtil.DiffResult result = DiffUtil.calculateDiff(usersDiffUtilCallback);
                    adapterData = model.getUsers();
                    adapter.setData(adapterData);
                    result.dispatchUpdatesTo(adapter);
                }
            }
        }
    }

    public interface OnItemClick {
        void onItemClick(@NonNull User user);
    }
}
