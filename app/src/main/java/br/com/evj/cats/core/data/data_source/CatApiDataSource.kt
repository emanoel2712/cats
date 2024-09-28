package br.com.evj.cats.core.data.data_source

interface CatApiDataSource {
    suspend fun fetchCats(): List<Int>
}