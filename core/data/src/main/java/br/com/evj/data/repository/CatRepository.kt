package br.com.evj.data.repository

interface CatRepository {
    suspend fun fetchCats()
}