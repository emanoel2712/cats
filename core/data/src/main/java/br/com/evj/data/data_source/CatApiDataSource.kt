package br.com.evj.data.data_source

import retrofit2.http.GET

interface CatApiDataSource {

    @GET
    suspend fun fetchCats()
}