package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Book(
    val id: String,
    val selfLink: String?,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?
)

@Immutable
@Serializable
data class BooksList(
    val items: List<Book>? = emptyList()
)





