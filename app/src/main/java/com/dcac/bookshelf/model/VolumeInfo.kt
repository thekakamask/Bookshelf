package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import com.dcac.bookshelf.utils.toHttps
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = emptyList(),
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = emptyList(),
    val imageLinks: ImageLinks? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = emptyList(),
    val language: String,
    val canonicalVolumeLink: String? = null,
    val maturityRating: String? = null
) {
    val secureThumbnail: String?
        get() = imageLinks?.thumbnail.toHttps()

    val secureSmallThumbnail: String?
        get() = imageLinks?.smallThumbnail.toHttps()
}

@Immutable
@Serializable
data class ImageLinks(
    val smallThumbnail: String? =null ,
    val thumbnail: String? = null
)


@Immutable
@Serializable
data class IndustryIdentifier(
    val type: String,
    val identifier: String
)

