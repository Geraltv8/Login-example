package com.utngoya.login.restClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val BASE_URL = "https://private-617bfa-cursosapi.apiary-mock.com/"

    val instance: CursosApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CursosApi::class.java)
    }
}