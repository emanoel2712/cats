package br.com.evj.imgur.listing

import androidx.paging.PagingData
import br.com.evj.model.GalleryItem

sealed class GalleryState {
    object Loading : GalleryState()
    data class Success(val galleryItems: PagingData<GalleryItem>) : GalleryState()
    data class Error(val message: String) : GalleryState()
}