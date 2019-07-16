package com.example.homework_2.data.network

import com.example.homework_2.data.model.LoginForm
import com.example.homework_2.data.model.RegistrationForm
import io.reactivex.Single
import retrofit2.http.POST

interface AuthApiService {

    @POST("login")
    fun login():Single<LoginForm>

    @POST("registration")
    fun registration():Single<RegistrationForm>
}