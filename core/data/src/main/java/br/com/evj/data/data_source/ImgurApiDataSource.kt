package br.com.evj.data.data_source

import br.com.evj.data.response.CatResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiDataSource {
    @GET("gallery/search")
    suspend fun fetchGallery(@Query("q") query: String): List<CatResponse>
}