package com.example.homework_2

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        //статичная переменная
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        //в статичную переменную запишем себя
        instance = this
    }

    //Переопределяем геттеры и сеттеры
    var token: String?
        get() = getSharedPreferences("auth", Context.MODE_PRIVATE).getString("token", null)
        set(value) = getSharedPreferences("auth", Context.MODE_PRIVATE).edit().putString("token", value).apply()
}