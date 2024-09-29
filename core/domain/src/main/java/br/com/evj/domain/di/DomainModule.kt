package br.com.evj.domain.di

import br.com.evj.domain.use_case.FetchGalleryUseCase
import br.com.evj.domain.use_case.FetchGalleryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideFetchCatsUseCase(fetchCatsUseCaseImpl: FetchGalleryUseCaseImpl): FetchGalleryUseCase {
        return fetchCatsUseCaseImpl
    }
}
