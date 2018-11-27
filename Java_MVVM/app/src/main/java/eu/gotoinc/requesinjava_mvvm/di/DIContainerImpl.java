package eu.gotoinc.requesinjava_mvvm.di;


import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;
import eu.gotoinc.requesinjava_mvvm.repository.ApiContractImpl;
import eu.gotoinc.requesinjava_mvvm.repository.init.RetrofitInit;
import eu.gotoinc.requesinjava_mvvm.repository.init.RetrofitInitImpl;

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
