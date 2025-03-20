package com.dcac.bookshelf.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dcac.bookshelf.R

fun String?.toHttps(): String? {
    return this?.replace("http://", "https://")
}

@Composable
fun List<String>?.toDisplayStringAuthors(): String {
    return this?.joinToString(separator = ", ") ?: stringResource(R.string.no_authors)
}

@Composable
fun List<String>?.toDisplayStringCategories(): String {
    return this?.joinToString(separator = ", ") ?: stringResource(R.string.no_categories)
}