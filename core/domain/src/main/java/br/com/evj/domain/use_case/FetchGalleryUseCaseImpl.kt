package br.com.evj.domain.use_case

import br.com.evj.data.repository.ImgurRepository
import br.com.evj.model.ImageDetail
import javax.inject.Inject

class FetchGalleryUseCaseImpl @Inject constructor(private val imgurRepository: ImgurRepository)
    : FetchGalleryUseCase {
    override suspend fun fetchCats(): Result<List<ImageDetail>> {
        return imgurRepository.fetchGallery()
    }
}