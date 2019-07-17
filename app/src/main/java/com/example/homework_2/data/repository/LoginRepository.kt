package com.example.homework_2.data.repository

import com.example.homework_2.data.model.LoginForm
import com.example.homework_2.data.model.RegistrationForm
import com.example.homework_2.data.model.TokenWrap
import com.example.homework_2.data.network.APIClient
import io.reactivex.Completable
import io.reactivex.Single

class LoginRepository : ILoginRepository {

    //Singleton
    companion object {
        val instance: LoginRepository by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = LoginRepository()
    }

    //Создаем переменную authApiService для регистрации
    private val authApiService = APIClient.instance.authApiService


    override fun registration(name: String, phone: String): Completable {
        return authApiService.registration(RegistrationForm(phone, name))
    }

    override fun login(phone: String): Completable {
        return authApiService.registration(RegistrationForm(phone))
    }

    override fun checkCode(phone: String, code: String): Single<TokenWrap> {
        return authApiService.login(LoginForm(phone, code))
    }

}