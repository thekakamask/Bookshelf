package com.dcac.bookshelf.ui.screens

import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import kotlinx.coroutines.flow.StateFlow

interface IBookshelfViewModel {

    val uiState: StateFlow<BookshelfUiState>
    fun resetHomeScreenStates()
    fun retryLoading()
    fun updateUserGoogleKeyWord(userGoogleKeyWord: String)
    fun searchBooks(userGoogleKeyWord: String)
    fun updateCurrentBook(book: Book)
}