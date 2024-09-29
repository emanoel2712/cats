package br.com.evj.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.evj.data.data_source.GalleryPagingDataSource
import br.com.evj.data.data_source.ImgurApiDataSource
import br.com.evj.model.GalleryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImgurRepositoryImpl @Inject constructor(
    private val imgurApiDataSource: ImgurApiDataSource
) : ImgurRepository {

    private var cachedPagingSource: GalleryPagingDataSource? = null

    override fun fetchGallery(search: String): Flow<PagingData<GalleryItem>> {
        if (cachedPagingSource == null) {
            cachedPagingSource = GalleryPagingDataSource(imgurApiDataSource, search)
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { cachedPagingSource!! }
        ).flow
    }
}