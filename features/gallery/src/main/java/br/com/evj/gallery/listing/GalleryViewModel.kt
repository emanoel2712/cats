package br.com.evj.gallery.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.evj.domain.use_case.FetchGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val fetchGalleryUseCase: FetchGalleryUseCase) : ViewModel() {
//    private val _state = MutableStateFlow(CatsState())
//    val state = _state.asStateFlow()

    fun fetchCats() {
        viewModelScope.launch {
            fetchGalleryUseCase.fetchCats()
                .onSuccess {

                }
                .onFailure {

                }
        }
    }
}