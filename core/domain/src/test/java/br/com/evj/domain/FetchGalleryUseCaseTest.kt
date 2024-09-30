package br.com.evj.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import androidx.paging.PagingData
import br.com.evj.data.repository.ImgurRepository
import br.com.evj.domain.use_case.FetchGalleryUseCase
import br.com.evj.model.GalleryItem
import org.junit.jupiter.api.Assertions

class FetchGalleryUseCaseTest {
    private val imgurRepository: ImgurRepository = mockk()

    private lateinit var fetchGalleryUseCase: FetchGalleryUseCase

    @BeforeEach
    fun setUp() {
        fetchGalleryUseCase = FetchGalleryUseCase(imgurRepository)
    }

    @Test
    fun `fetchGallery calls repository and collects flow`() = runTest {
        val galleryItemsFlow: Flow<PagingData<GalleryItem>> = flowOf(PagingData.empty())
        val searchQuery = "cats"

        coEvery { imgurRepository.fetchGallery(searchQuery) } returns galleryItemsFlow

        val result = fetchGalleryUseCase.fetchGallery(searchQuery)

        coVerify(exactly = 1) { imgurRepository.fetchGallery(searchQuery) }

        try {
            result.collect()
            Assertions.assertTrue(true)
        } catch (e: Exception) {
            Assertions.assertTrue(
                false,
                "Exception was thrown during flow collection: ${e.message}"
            )
        }
    }
}