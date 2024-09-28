package br.com.evj.data.repository

import br.com.evj.data.data_source.CatApiDataSource
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val catApiDataSource: CatApiDataSource): CatRepository {
    override suspend fun fetchCats() {
        TODO("Not yet implemented")
    }
}