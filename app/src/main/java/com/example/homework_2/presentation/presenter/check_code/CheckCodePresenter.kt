package com.example.homework_2.presentation.presenter.check_code

import com.example.homework_2.App
import com.example.homework_2.data.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckCodePresenter(private val view: ICheckCodeView) {

    private val repository = LoginRepository.instance

    fun checkCode(phone: String, code: String) {
        repository.checkCode(phone, code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Засовываем в файлик
                App.instance.token = it.token
                view.trueCode()
            }, {
                view.showError(it.message ?: "Unknown error")
            })
    }
}