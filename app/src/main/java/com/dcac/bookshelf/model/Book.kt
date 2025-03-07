package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Book(
    val id: String,
    val selfLink: String? = null,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null
)

@Immutable
@Serializable
data class BooksList(
    val items: List<Book>? = emptyList()
)





