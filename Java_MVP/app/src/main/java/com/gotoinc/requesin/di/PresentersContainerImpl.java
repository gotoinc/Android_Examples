package com.gotoinc.requesin.di;

import android.util.Log;

import com.gotoinc.requesin.TestApp;
import com.gotoinc.requesin.mvp.common.mvp.MvpPresenter;
import com.gotoinc.requesin.mvp.home.HomeContract;
import com.gotoinc.requesin.mvp.home.HomeModel;
import com.gotoinc.requesin.mvp.home.HomePresenter;
import com.gotoinc.requesin.repository.init.RetrofitInit;
import com.gotoinc.requesin.repository.init.RetrofitInitImpl;

import java.util.HashMap;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public class PresentersContainerImpl implements PresentersContainer {
    private static volatile PresentersContainerImpl INSTANCE;

    private final RetrofitInit retrofitInit;
    private final HashMap<String, MvpPresenter> presentersStorage = new HashMap<>();

    private PresentersContainerImpl() {
        retrofitInit = new RetrofitInitImpl();
    }

    public static PresentersContainerImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (PresentersContainerImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PresentersContainerImpl();
                }
            }
        }
        return INSTANCE;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends MvpPresenter> T of(@NonNull String tag, @NonNull Class<T> mvpPresenter) {
        if(mvpPresenter.isAssignableFrom(HomePresenter.class)) {
            if(presentersStorage.containsKey(tag)) {
                Log.d("myLog", "get presenter from storage: " + tag);
                return (T) presentersStorage.get(tag);
            } else {
                Log.d("myLog", "create new presenter: " + tag);
                HomeContract.Model model = new HomeModel();
                MvpPresenter presenter = new HomePresenter(model, retrofitInit.get(), TestApp.getAppContext());
                presentersStorage.put(tag, presenter);
                return (T) presenter;
            }
        }

        return null;
    }

    @Override
    public void release(@NonNull String tag) {
        Log.d("myLog", "release: " + tag);
        if(presentersStorage.get(tag) != null) presentersStorage.remove(tag);
    }
}
