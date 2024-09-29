package br.com.evj.gallery.listing

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatsScreen() {
//    val viewModel by viewModels<CatsViewModel>()
//    val viewModel: CatsViewModel by viewModel<CatsViewModel>()

    val viewModel = hiltViewModel<GalleryViewModel>()

    viewModel.fetchCats()

//    val api: Fetc
//    val state by viewModel.state.collectAsState()
//

}