package com.example.homework_2.data.network

import com.example.homework_2.data.model.LoginForm
import com.example.homework_2.data.model.RegistrationForm
import com.example.homework_2.data.model.TokenWrap
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    //В шпионе будет только это
    @POST("login")
    fun login(@Body loginForm: LoginForm): Single<TokenWrap>

    @POST("registration")
    //Completable - реактивное true/false
    fun registration(@Body registrationForm: RegistrationForm): Completable
}