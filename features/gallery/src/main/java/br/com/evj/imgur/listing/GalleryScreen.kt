package br.com.evj.imgur.listing

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GalleryScreen() {
//    val viewModel by viewModels<CatsViewModel>()
//    val viewModel: CatsViewModel by viewModel<CatsViewModel>()

    val viewModel = hiltViewModel<GalleryViewModel>()

    viewModel.fetchGallery()

//    val api: Fetc
//    val state by viewModel.state.collectAsState()
//

}