package br.com.evj.data.data_source

import br.com.evj.data.response.Container
import br.com.evj.data.response.GalleryItemResponse
import br.com.evj.model.GalleryItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiDataSource {
    @GET("gallery/search/{page}")
    suspend fun fetchGallery(@Path("page") page: Int, @Query("q") query: String): Container<List<GalleryItemResponse>?>?
}