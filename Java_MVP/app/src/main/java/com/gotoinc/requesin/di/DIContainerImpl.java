package com.gotoinc.requesin.di;

import com.gotoinc.requesin.repository.ApiContract;
import com.gotoinc.requesin.repository.ApiContractImpl;
import com.gotoinc.requesin.repository.init.RetrofitInit;
import com.gotoinc.requesin.repository.init.RetrofitInitImpl;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public class DIContainerImpl implements DIContainer {
    private static volatile DIContainerImpl INSTANCE;

    private ApiContract queries;

    private DIContainerImpl() {
        RetrofitInit retrofitInit = new RetrofitInitImpl();
        this.queries = new ApiContractImpl(retrofitInit.get());
    }

    public static DIContainerImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (DIContainerImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DIContainerImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ApiContract getQueries() {
        return queries;
    }
}
