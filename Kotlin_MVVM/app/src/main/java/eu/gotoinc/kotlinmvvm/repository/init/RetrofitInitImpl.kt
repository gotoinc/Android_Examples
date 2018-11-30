package eu.gotoinc.kotlinmvvm.repository.init

import com.google.gson.GsonBuilder
import eu.gotoinc.kotlinmvvm.repository.HOSTNAME
import eu.gotoinc.kotlinmvvm.repository.RetrofitRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInitImpl : RetrofitInit {
    override val retrofitQueries: RetrofitRequest

    init {
        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        builder.setLenient()
        val gson = builder.create()
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        retrofitQueries = retrofit.create(RetrofitRequest::class.java)
    }
}