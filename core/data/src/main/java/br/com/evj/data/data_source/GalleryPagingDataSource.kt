package br.com.evj.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.evj.data.response.toModel
import br.com.evj.model.GalleryItem
import javax.inject.Inject

class GalleryPagingDataSource @Inject constructor(private val api: ImgurApiDataSource, private val search: String = "") : PagingSource<Int, GalleryItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.fetchGallery(nextPageNumber, search)?.data ?: listOf()
            val mappedList = response.map { it.toModel() }

            LoadResult.Page(
                data = mappedList,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (mappedList.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        } ?: 0
    }
}