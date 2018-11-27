package eu.gotoinc.requesinjava_mvvm.repository;


import java.util.List;

import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.PaginationResponse;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequest {
    @GET(ApiConsts.API_VERSION + ApiConsts.LIST_OF_USERS)
    Single<PaginationResponse<List<UserResponse>>> getUsersList(@Query("page")int page);
}
