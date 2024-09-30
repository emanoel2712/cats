package br.com.evj.imgur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.evj.domain.use_case.FetchGalleryUseCase
import br.com.evj.model.GalleryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    fetchGalleryUseCase: FetchGalleryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<GalleryState>(GalleryState.Loading)
    val state: StateFlow<GalleryState> = _state.asStateFlow()

    val galleryItemsFlow: Flow<PagingData<GalleryItem>> =
        fetchGalleryUseCase.fetchGallery("cats")
            .cachedIn(viewModelScope)

    init {
        fetchGallery()
    }

    private fun fetchGallery() {
        viewModelScope.launch {
            galleryItemsFlow
                .onStart { _state.value = GalleryState.Loading }
                .catch { exception -> _state.value =
                    GalleryState.Error(exception.message ?: "Unknown error")
                }
                .collect { galleryItems -> _state.value = GalleryState.Success(galleryItems) }
        }
    }
}