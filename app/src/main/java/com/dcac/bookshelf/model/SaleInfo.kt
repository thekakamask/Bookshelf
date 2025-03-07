package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String? = null,
    val isEbook: Boolean? = null,
    val listPrice: PriceInfo? = null,
    val retailPrice: PriceInfo? = null
)

@Immutable
@Serializable
data class PriceInfo(
    val amount: Double? = null,
    val currencyCode: String?= null
)
