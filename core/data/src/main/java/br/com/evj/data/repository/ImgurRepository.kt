package br.com.evj.data.repository

import androidx.paging.PagingData
import br.com.evj.model.GalleryItem
import kotlinx.coroutines.flow.Flow

interface ImgurRepository {
    fun fetchGallery(search: String): Flow<PagingData<GalleryItem>>
}