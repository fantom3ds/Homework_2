package com.example.homework_2.presentation.presenter.registration

import com.example.homework_2.data.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterPresenter(private val view: IRegisterView) {

    private val repository = LoginRepository.instance

    fun register(name: String, phone: String) {
        repository.registration(name, phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.successRegister()
            }, {
                view.showError(it.message ?: "Unknown error")
            })
    }

}