package com.gotoinc.requesin.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gotoinc.requesin.R;
import com.gotoinc.requesin.di.DIContainerImpl;
import com.gotoinc.requesin.repository.ApiContract;
import com.gotoinc.requesin.view.BaseViewContract;
import com.gotoinc.requesin.view._model.User;
import com.gotoinc.requesin.view.detail.DetailFragment;

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
public class HomeFragment extends Fragment implements HomeContract {
    private RecyclerView rvUsers;
    private UsersAdapter adapter;

    private ApiContract queries;

    private int nextPage = 1;
    private boolean isExistNextPage = true;
    private boolean isLoading;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);

        queries = DIContainerImpl.getInstance().getQueries();
        if(getActivity() != null)
            queries.getUsersList(nextPage,this, ((BaseViewContract) getActivity()));
        initList();

        return view;
    }

    private void findViews(View view) {
        rvUsers = view.findViewById(R.id.rv_users);
    }

    private void initList() {
        adapter = new UsersAdapter(getContext(), new ArrayList<>(), user -> getFragmentManager().beginTransaction()
                .add(R.id.container, DetailFragment.getInstance(user), DetailFragment.class.getSimpleName())
                .addToBackStack(HomeFragment.class.getSimpleName())
                .commit());
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
                        if (isExistNextPage && !isLoading) {
                            isLoading = true;
                            if(getActivity() != null)
                                queries.getUsersList(nextPage,HomeFragment.this, ((BaseViewContract) getActivity()));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setUsers(List<User> users) {
        List<User> data = new ArrayList<>();
        data.addAll(adapter.getData());
        data.addAll(users);
        UsersDiffUtilCallback usersDiffUtilCallback = new UsersDiffUtilCallback(adapter.getData(), data);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(usersDiffUtilCallback);
        adapter.setData(data);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public void nextPage(boolean isExist, int nextPage) {
        isExistNextPage = isExist;
        isLoading = false;
        if(isExist) {
            this.nextPage = nextPage;
        }
    }

    interface OnItemClick {
        void onItemClick(@NonNull User user);
    }
}
