package com.example.homework_2.data.model

class LoginForm(
    val phone: String,
    val password: String,
    val platform: String = "Android",
    val version: String = "androidSchool 0.1"
)