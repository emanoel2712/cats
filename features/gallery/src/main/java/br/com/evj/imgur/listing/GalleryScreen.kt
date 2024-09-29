package br.com.evj.imgur.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.evj.model.GalleryItem
import coil.compose.AsyncImage

@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val lazyPagingItems = viewModel.galleryItemsFlow.collectAsLazyPagingItems()

    when (state) {
        is GalleryState.Loading -> {
            CircularProgressIndicator()
        }

        is GalleryState.Error -> {
            Text(text = "Error: ${(state as GalleryState.Error).message}")
        }

        is GalleryState.Success -> {
            PagingGalleryList(lazyPagingItems)
        }
    }
}

@Composable
fun LoadThumbnail(url: String) {
    AsyncImage(
        model = url,
        contentDescription = url,
        Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(100.dp),
        placeholder = ColorPainter(Color.Gray),
    )
}

@Composable
fun PagingGalleryList(lazyPagingItems: LazyPagingItems<GalleryItem>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyGridState(),
        ) {
            items(lazyPagingItems.itemCount) { index ->
                lazyPagingItems[index]?.let { galleryItem ->
                    LoadThumbnail(url = galleryItem.url)
                }
            }

            lazyPagingItems.apply {
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                    item { Spacer(modifier = Modifier.height(100.dp)) } // Placeholder for loading
                }

                if (loadState.append is LoadState.Error) {
                    item {
                        ErrorItem { retry() }
                    }
                }
            }
        }

        if (lazyPagingItems.loadState.refresh is LoadState.Loading || lazyPagingItems.loadState.append is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun ErrorItem(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error loading more items")
        Button(onClick = onRetry) {
            Text("Try Again")
        }
    }
}