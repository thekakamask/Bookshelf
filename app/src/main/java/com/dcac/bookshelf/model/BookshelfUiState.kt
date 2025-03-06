package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable

sealed interface BookshelfUiState {

    @Immutable
    data class Success(
        val booksList: List<Book>,
        val filteredBooksList: List<Book>? = null,
        val currentBook : Book? = null,
        val isShowingDetailsBook : Boolean
    ) : BookshelfUiState

    data class Error (
        val message : String
    ) : BookshelfUiState

    data object Loading : BookshelfUiState

}