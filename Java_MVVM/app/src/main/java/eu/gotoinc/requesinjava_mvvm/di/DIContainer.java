package eu.gotoinc.requesinjava_mvvm.di;


import eu.gotoinc.requesinjava_mvvm.repository.ApiContract;

public interface DIContainer {
    ApiContract getQueries();
}
