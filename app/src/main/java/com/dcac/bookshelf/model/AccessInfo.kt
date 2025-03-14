package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: EpubInfo? = null,
    val pdf: PdfInfo? = null
)

@Immutable
@Serializable
data class EpubInfo(
    val isAvailable: Boolean,
    val acsTokenLink: String? = null
)

@Immutable
@Serializable
data class PdfInfo(
    val isAvailable: Boolean,
    val acsTokenLink: String? = null
)
