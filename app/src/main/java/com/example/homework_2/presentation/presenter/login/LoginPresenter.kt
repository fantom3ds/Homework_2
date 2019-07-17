package com.example.homework_2.presentation.presenter.login

import com.example.homework_2.data.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(private val view: ILoginView) {

    private val repository = LoginRepository.instance

    fun login(phone: String) {
        repository.login(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.successLogin(phone)
            },{
                view.showError(it.message?:"Unknown error")
            })
    }
}