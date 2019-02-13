package com.gotoinc.requesin.mvp.home;

import android.util.Log;

import com.gotoinc.requesin.mvp.common.callback.UsersLoadedCallback;
import com.gotoinc.requesin.mvp.common.data_model.User;
import com.gotoinc.requesin.repository.RetrofitRequest;

import java.io.IOException;
import java.net.SocketTimeoutException;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public class HomeModel implements HomeContract.Model {
    private final RetrofitRequest request;

    public HomeModel(RetrofitRequest request) {
        this.request = request;
    }

    @Override
    public Disposable getUsersList(int page, @NonNull UsersLoadedCallback callback) {
        return request.getUsersList(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(response -> {
                Log.d("myLog", "list of users got");
                callback.usersLoaded(User.from(response.getData()), response.getTotalPages() > page);
            }, e -> {
                    e.printStackTrace();
                    if (e instanceof HttpException) callback.onError();
                    else if (e instanceof SocketTimeoutException) callback.onTimeout();
                    else if (e instanceof IOException) callback.onNetworkError();
                    else callback.onError(e.getMessage());
                });
    }
}
