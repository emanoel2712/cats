package br.com.evj.imgur

import androidx.paging.PagingData
import br.com.evj.domain.use_case.FetchGalleryUseCase
import br.com.evj.model.GalleryItem
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
class GalleryViewModelTest {
    private lateinit var fetchGalleryUseCase: FetchGalleryUseCase
    private lateinit var viewModel: GalleryViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        fetchGalleryUseCase = mockk()
        viewModel = GalleryViewModel(fetchGalleryUseCase)
    }

    @Test
    fun `fetchGallery should update state to Loading and then Success`() = testScope.runTest {
        val expectedItems = PagingData.from(listOf(GalleryItem("123", "Test Title", "https://example.com/image.jpg")))
        coEvery { fetchGalleryUseCase.fetchGallery("cats") } returns flowOf(expectedItems)

        val states = mutableListOf<GalleryState>()
        val job = testScope.launch {
            viewModel.state.collect { states.add(it) }
        }

        advanceTimeBy(1000)

        assertEquals(GalleryState.Loading, states[0])
        assertEquals(GalleryState.Success(expectedItems), states[1])

        verify { fetchGalleryUseCase.fetchGallery("cats") }

        job.cancel()
    }
}