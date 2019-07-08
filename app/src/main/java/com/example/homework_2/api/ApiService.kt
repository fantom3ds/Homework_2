package com.example.homework_2.api

import com.example.homework_2.models.Party
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    fun getParties(): Single<List<Party>>
}