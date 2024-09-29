package br.com.evj.data.repository

import br.com.evj.data.data_source.ImgurApiDataSource
import br.com.evj.data.response.toModel
import br.com.evj.model.ImageDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImgurRepositoryImpl @Inject constructor(private val imgurApiDataSource: ImgurApiDataSource) :
    ImgurRepository {
    override suspend fun fetchGallery(): Result<List<ImageDetail>> {
        return try {
            val response = imgurApiDataSource.fetchGallery("dogs")
            val gallery = response.map { it.toModel() }
            Result.success(gallery)
        } catch (e: IOException) {
            Result.failure(Exception(e.message ?: "Unknown error"))
        } catch (e: HttpException) {
            /*Result.failure(
                Exception(
                    e.response()?.errorBody()?.getErrorMsg() ?: StringUtils.getString(
                        R.string.error_unknown
                    )
                )
            )*/
            Result.failure(Exception(e.message ?: "Unknown error"))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}