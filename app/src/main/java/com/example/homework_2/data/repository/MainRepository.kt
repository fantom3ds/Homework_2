package com.example.homework_2.data.repository

import com.example.homework_2.data.model.Party
import com.example.homework_2.data.network.APIClient
import com.example.homework_2.data.network.ApiService
import io.reactivex.Single

class MainRepository : IMainRepository {

    //Singleton
    companion object {
        val instance: MainRepository by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = MainRepository()
    }



    private val apiService = APIClient.instance.apiService

    override fun getParties(): Single<List<Party>> {
        return apiService.getParties()
    }
}