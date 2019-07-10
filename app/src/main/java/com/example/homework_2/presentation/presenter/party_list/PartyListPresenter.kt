package com.example.homework_2.presentation.presenter.party_list

import android.widget.Toast
import com.example.homework_2.data.model.Party
import com.example.homework_2.data.repository.IMainRepository
import com.example.homework_2.data.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PartyListPresenter(private val view: IPartyListView) {

    //для синглтона
    private val repository = MainRepository.instance

    //2 - презентер получает вечеринки, и когда получил - вызывает метод вьюхи, который отображает полученные данные
    fun getParties() {
        view.setLoading(true)
        repository.getParties()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showParties(it)
                //Прекращаем показ экрана загрузки
                view.setLoading(false)
            }, {
                //Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                //Вызываем метод показа ошибки
                view.showError(it.message ?: "unknown error")
            })
    }

}