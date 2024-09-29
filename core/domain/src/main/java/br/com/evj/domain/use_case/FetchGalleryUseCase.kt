package br.com.evj.domain.use_case

import br.com.evj.model.ImageDetail

interface FetchGalleryUseCase {
    suspend fun fetchGallery(): Result<List<ImageDetail>>
}