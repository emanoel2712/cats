package br.com.evj.domain.use_case

import androidx.paging.PagingData
import br.com.evj.data.repository.ImgurRepository
import br.com.evj.model.GalleryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchGalleryUseCase @Inject constructor(private val imgurRepository: ImgurRepository)  {
    fun fetchGallery(search: String): Flow<PagingData<GalleryItem>> {
        return imgurRepository.fetchGallery(search)
    }
}