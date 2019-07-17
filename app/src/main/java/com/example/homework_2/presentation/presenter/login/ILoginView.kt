package com.example.homework_2.presentation.presenter.login

interface ILoginView {

    fun showError(error: String)

    //fun setLoading(isLoading: Boolean)

    fun successLogin(phone: String)
}