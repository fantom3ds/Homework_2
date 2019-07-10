package com.example.homework_2.data.network

import com.example.homework_2.data.model.Party
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    fun getParties(): Single<List<Party>>
}