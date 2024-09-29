package br.com.evj.data.di

import br.com.evj.data.data_source.ImgurApiDataSource
import br.com.evj.data.repository.ImgurRepository
import br.com.evj.data.repository.ImgurRepositoryImpl
import br.com.evj.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideImgurApiDataSource(serviceProvider: ServiceProvider): ImgurApiDataSource {
        return serviceProvider.createService(ImgurApiDataSource::class.java)
    }

    @Provides
    fun provideImgurRepository(imgurRepositoryImpl: ImgurRepositoryImpl): ImgurRepository {
        return imgurRepositoryImpl
    }
}