package com.example.homework_2.data.repository

import com.example.homework_2.data.model.TokenWrap
import io.reactivex.Completable
import io.reactivex.Single

interface ILoginRepository {

    fun registration(name: String, phone: String):Completable

    fun login(phone: String):Completable

    fun checkCode(phone: String, code: String):Single<TokenWrap>
}