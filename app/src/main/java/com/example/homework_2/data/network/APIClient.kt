package com.example.homework_2.data.network

import com.example.homework_2.App
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
        //val instance = Holder.INSTANCE
        //val instance = APIClient()
    }

    private object Holder {
        val INSTANCE = APIClient()
    }

    //Переменная для запроса списка вечеринок
    val apiService: ApiService
    //Переменная для авторизации и регистрации
    val authApiService: AuthApiService

    //init можно создать общий
    //Блок, который будет выполнен при создании класса, инициализирует то что внутри
    init {
        authApiService = createRetrofit(
            "http://beta-secothon.profsoft.online/api/",
            initOkHttpClient()
        ).create(AuthApiService::class.java)

        apiService = createRetrofit(
            "http://beta-secothon.profsoft.online/api/",
            initOkHttpClientWithAuth()
        ).create(ApiService::class.java)
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

    private fun initOkHttpClientWithAuth(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(5, TimeUnit.SECONDS)
            addInterceptor { chain ->
                var request = chain.request()
                //запускаем билдер для добавления Header'а
                request = request.newBuilder().addHeader("apikey", App.instance.token).build()
                chain.proceed(request)
            }

            //Как в предыдущем запросе
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
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