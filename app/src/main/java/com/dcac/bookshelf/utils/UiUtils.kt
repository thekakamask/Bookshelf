package com.dcac.bookshelf.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dcac.bookshelf.R

fun String?.toHttps(): String? {
    return this?.replace("http://", "https://")
}

@Composable
fun List<String>?.toDisplayString(): String {
    return this?.joinToString(separator = ", ") ?: stringResource(R.string.no_authors)
}