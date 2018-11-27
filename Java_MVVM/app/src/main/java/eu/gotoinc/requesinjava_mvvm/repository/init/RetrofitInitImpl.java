package eu.gotoinc.requesinjava_mvvm.repository.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import eu.gotoinc.requesinjava_mvvm.repository.ApiConsts;
import eu.gotoinc.requesinjava_mvvm.repository.RetrofitRequest;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitImpl implements RetrofitInit {
    private RetrofitRequest retrofitQueries;

    public RetrofitInitImpl() {
        init();
    }

    private void init() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setLenient();
        Gson gson = builder.create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConsts.HOSTNAME)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        retrofitQueries = retrofit.create(RetrofitRequest.class);
    }

    @Override
    public RetrofitRequest get() {
        return retrofitQueries;
    }
}
