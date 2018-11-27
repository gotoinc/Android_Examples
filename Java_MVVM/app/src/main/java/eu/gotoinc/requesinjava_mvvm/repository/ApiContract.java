package eu.gotoinc.requesinjava_mvvm.repository;


import java.util.List;

import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.PaginationResponse;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import io.reactivex.Single;

public interface ApiContract {
    Single<PaginationResponse<List<UserResponse>>> getUsersList(int page);
}
