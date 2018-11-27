package eu.gotoinc.requesinjava_mvvm.repository;

import java.util.List;

import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.PaginationResponse;
import eu.gotoinc.requesinjava_mvvm.repository.data_models.responses.UserResponse;
import io.reactivex.Single;

public class ApiContractImpl implements ApiContract {
    private RetrofitRequest queries;

    public ApiContractImpl(RetrofitRequest queries) {
        this.queries = queries;
    }

    @Override
    public Single<PaginationResponse<List<UserResponse>>> getUsersList(int page) {
        return queries.getUsersList(page);
    }
}
