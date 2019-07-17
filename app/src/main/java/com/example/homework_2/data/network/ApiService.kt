package com.example.homework_2.data.network

import com.example.homework_2.data.model.Party
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("party/")
    fun getParties(): Single<List<Party>>
}