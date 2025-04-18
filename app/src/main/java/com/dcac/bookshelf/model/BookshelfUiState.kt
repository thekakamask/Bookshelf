package com.dcac.bookshelf.model

import androidx.compose.runtime.Immutable

sealed interface BookshelfUiState {

    @Immutable
    data class Success(
        val booksList: List<Book>,
        val currentBook : Book? = null,
        val isShowingDetailsBook : Boolean = false,
        val userGoogleKeyWord : String
    ) : BookshelfUiState

    data class Error (
        val message : String,
        val userGoogleKeyWord: String
    ) : BookshelfUiState

    data object Loading : BookshelfUiState

}