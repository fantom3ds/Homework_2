package com.example.homework_2.presentation.presenter.check_code

import com.example.homework_2.data.repository.LoginRepository

interface ICheckCodeView {

    fun trueCode()

    fun showError(message:String)

    //сюда еще добавить загрузку
}