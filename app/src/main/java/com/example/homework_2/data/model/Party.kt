package com.example.homework_2.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Party(
    var name: String? = "",
    var date: Long? = 0,
    var currentBalance: Int? = 0,

    //Будет парсить сюда значение "balance"
    @SerializedName("balance")
    var fullBalance: Int? = 0,

    var image: String? = "",
    var countNewEvent: Int? = 0,
    var inventedMe: Boolean? = false
): Serializable