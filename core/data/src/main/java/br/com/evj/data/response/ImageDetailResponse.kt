package br.com.evj.data.response

import br.com.evj.model.ImageDetail

data class CatResponse(val name: String?)

fun CatResponse.toModel(): ImageDetail {
    return ImageDetail(name = name ?: "")
}
