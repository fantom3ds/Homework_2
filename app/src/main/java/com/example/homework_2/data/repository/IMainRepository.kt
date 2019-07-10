package com.example.homework_2.data.repository

import com.example.homework_2.data.model.Party
import io.reactivex.Single

interface IMainRepository {
    fun getParties(): Single<List<Party>>
}