package com.dcac.bookshelf.fakeDataAndUtils

import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeDetailsSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeGridSuccessBookshelfUiState
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.ui.screens.IBookshelfViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeBookshelfViewModel(
    initialUiState: BookshelfUiState
) : IBookshelfViewModel {

    private val _uiState = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<BookshelfUiState> = _uiState

    var resetHomeScreenStatesCalled = false
        private set
    override fun resetHomeScreenStates() {
        resetHomeScreenStatesCalled = true
        _uiState.value = fakeGridSuccessBookshelfUiState
    }

    var retryLoadingCalled  = false
        private set
    override fun retryLoading() {
        retryLoadingCalled  = true
        _uiState.value = fakeGridSuccessBookshelfUiState
    }

    var updateUserGoogleKeyWordCalled = false
        private set
    override fun updateUserGoogleKeyWord(userGoogleKeyWord: String) {
        updateUserGoogleKeyWordCalled = true
        _uiState.value = fakeGridSuccessBookshelfUiState
    }

    var searchBooksCalled = false
        private set
    override fun searchBooks(userGoogleKeyWord: String) {
        searchBooksCalled = true
        _uiState.value = fakeGridSuccessBookshelfUiState
    }

    var updateCurrentBookCalled = false
        private set
    override fun updateCurrentBook(book: Book) {
        updateCurrentBookCalled = true
        _uiState.value = fakeDetailsSuccessBookshelfUiState
    }
}