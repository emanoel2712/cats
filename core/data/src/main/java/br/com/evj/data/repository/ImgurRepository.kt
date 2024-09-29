package br.com.evj.data.repository

import br.com.evj.model.ImageDetail

interface ImgurRepository {
    suspend fun fetchGallery(): Result<List<ImageDetail>>
}