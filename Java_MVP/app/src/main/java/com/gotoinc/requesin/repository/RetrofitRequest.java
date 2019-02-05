package com.gotoinc.requesin.repository;

import com.gotoinc.requesin.repository.data_models.responses.PaginationResponse;
import com.gotoinc.requesin.repository.data_models.responses.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public interface RetrofitRequest {
    @GET(ApiConsts.API_VERSION + ApiConsts.LIST_OF_USERS)
    Single<PaginationResponse<List<UserResponse>>> getUsersList(@Query("page") int page);
}
