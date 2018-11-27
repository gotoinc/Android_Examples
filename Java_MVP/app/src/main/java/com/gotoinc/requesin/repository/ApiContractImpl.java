package com.gotoinc.requesin.repository;

import android.util.Log;

import com.gotoinc.requesin.view.BaseViewContract;
import com.gotoinc.requesin.view._model.User;
import com.gotoinc.requesin.view.home.HomeContract;

import java.io.IOException;
import java.net.SocketTimeoutException;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public class ApiContractImpl implements ApiContract {
    private RetrofitRequest queries;

    public ApiContractImpl(RetrofitRequest queries) {
        this.queries = queries;
    }

    @Override
    public void getUsersList(int page, @NonNull HomeContract view, @NonNull BaseViewContract baseView) {
        queries.getUsersList(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                        if(response.body() != null) {
                            view.setUsers(User.from(response.body().getData()));
                            if(response.body().getTotalPages() > response.body().getPage()) {
                                view.nextPage(true, response.body().getPage() + 1);
                            } else {
                                view.nextPage(false, page);
                            }
                        }
                    }
                , e -> {
                        if (e instanceof HttpException) {
                            baseView.onError();
                        } else if (e instanceof SocketTimeoutException) {
                            baseView.onTimeout();
                        } else if (e instanceof IOException) {
                            baseView.onNetworkError();
                        } else {
                            baseView.onError(e.getMessage());
                        }
                    });
    }
}
