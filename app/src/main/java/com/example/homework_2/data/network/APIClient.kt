package com.example.homework_2.api

import com.example.homework_2.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    companion object {
        val instance: APIClient by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = APIClient()
    }

    val apiService: ApiService

    init {
        apiService = createRetrofit("http://5d1c669df31e7f00147eb5e5.mockapi.io/", initOkHttpClient()).create(ApiService::class.java)
    }

    //Без авторизации, для авторизованного нужно создать такой же, но с apikey
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()//для логирования запроса
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }
        }.build()
    }

    private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}