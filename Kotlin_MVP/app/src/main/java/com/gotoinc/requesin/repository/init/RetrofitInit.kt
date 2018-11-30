package com.gotoinc.requesin.repository.init

import com.google.gson.GsonBuilder
import com.gotoinc.requesin.repository.HOSTNAME
import com.gotoinc.requesin.repository.RetrofitRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Illia Derevianko on 29.11.18.
 * GoTo Inc.
 */
interface RetrofitInit {
    val requests: RetrofitRequest
}

class RetrofitInitImpl : RetrofitInit {
    override val requests: RetrofitRequest

    init {
        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation().setLenient()
        val retrofit = Retrofit.Builder()
            .baseUrl(HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create(builder.create()))
            .client(OkHttpClient())
            .build()

        requests = retrofit.create(RetrofitRequest::class.java)
    }
}