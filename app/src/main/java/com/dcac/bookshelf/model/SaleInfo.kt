package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String?,
    val isEbook: Boolean?,
    val listPrice: PriceInfo?,
    val retailPrice: PriceInfo?
)

@Immutable
@Serializable
data class PriceInfo(
    val amount: Double?,
    val currencyCode: String?
)
