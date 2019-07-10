package com.example.homework_2.presentation.presenter.party_list

import com.example.homework_2.data.model.Party

interface IPartyListView {
    //List<E> - интерфейс неизменяемого листа
    fun showParties(parties: List<Party>)
    fun showError(error: String)
    fun setLoading(isLoading: Boolean)
}