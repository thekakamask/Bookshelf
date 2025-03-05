package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = emptyList(),
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val categories: List<String>? = emptyList(),
    val imageLinks: ImageLinks?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val language: String,
    val canonicalVolumeLink: String?,
    val maturityRating: String?
)

@Immutable
@Serializable
data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)


@Immutable
@Serializable
data class IndustryIdentifier(
    val type: String,
    val identifier: String
)

