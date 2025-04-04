package com.dcac.bookshelf.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dcac.bookshelf.R

fun String?.toHttps(): String? {
    return this?.replace("http://", "https://")
}


fun List<String>?.toDisplayStringAuthors(): String {
    return this?.joinToString(separator = ", ") ?: R.string.no_authors.toString()
}


fun List<String>?.toDisplayStringCategories(): String {
    return this?.joinToString(separator = ", ") ?: R.string.no_categories.toString()
}

/*
fun String.toGoogleBooksQuery(): String {
    return this.trim()
        .split("\\s+".toRegex()) // Split on whitespace
        .joinToString("+") //add +
}*/
