package br.com.evj.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ServiceProvider @Inject constructor(client: OkHttpClient) {
    private val baseUrl = "https://escolabackteste.f10.com.br/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).client(client)
        .build()

    fun <API> createService(apiClass: Class<API>): API = retrofit.create(apiClass)
}