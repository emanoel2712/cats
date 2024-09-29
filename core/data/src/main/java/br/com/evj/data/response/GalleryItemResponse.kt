package br.com.evj.data.response

import br.com.evj.model.GalleryItem
import com.google.gson.annotations.SerializedName

data class Container<T>(
    val data: T? = null
)

data class GalleryItemResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("images")
    val images: List<ImageItemResponse>?
)

fun GalleryItemResponse.toModel(): GalleryItem {
    return GalleryItem(
        id = id ?: "",
        title = title ?: "",
        url = images?.firstOrNull { it.link?.contains("png") == true || it.link?.contains("jpeg") == true }?.link ?: "https://img.freepik.com/psd-gratuitas/belo-retrato-de-gato-isolado_23-2150186184.jpg"
    )
}

data class ImageItemResponse(
    @SerializedName("link")
    val link: String?,
)
