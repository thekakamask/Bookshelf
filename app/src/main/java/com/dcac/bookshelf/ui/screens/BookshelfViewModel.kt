package com.dcac.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dcac.bookshelf.BookshelfApplication
import com.dcac.bookshelf.data.BookshelfRepository
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class BookshelfViewModel(
    private val bookshelfRepository: BookshelfRepository
    //for unit testing
    //var bookshelfRepository: BookshelfRepository
) : ViewModel(), IBookshelfViewModel {

    private val _uiState = MutableStateFlow<BookshelfUiState>(BookshelfUiState.Loading)
    override val uiState: StateFlow<BookshelfUiState> = _uiState.asStateFlow()

    init {
        initUiState()
    }

    private fun initUiState() {
        viewModelScope.launch {
            _uiState.value = BookshelfUiState.Success(
                userGoogleKeyWord = "",
                booksList = emptyList()
            )

        }
    }

    private fun getBooksList(keyWord: String) {
        viewModelScope.launch {
            _uiState.value = BookshelfUiState.Loading
            try {
                val booksList = bookshelfRepository.getBooksList(keyWord)
                _uiState.value = BookshelfUiState.Success(
                    booksList = booksList,
                    userGoogleKeyWord = keyWord
                )
            } catch (e: IOException) {
                _uiState.value = BookshelfUiState.Error("Network error, please check your connection.",
                    userGoogleKeyWord = keyWord)
                println("❌ Network error: ${e.message}")
            } catch (e: HttpException) {
                _uiState.value = BookshelfUiState.Error("Server error: ${e.code()}",
                    userGoogleKeyWord = keyWord)
                println("❌ HTTP error: ${e.code()}")
            } catch (e: Exception) {
                _uiState.value = BookshelfUiState.Error("Unexpected error: ${e.message}",
                    userGoogleKeyWord = keyWord)
                println("❌ Unexpected error: ${e.message}")
            }
        }
    }

    override fun updateCurrentBook(book: Book) {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    isShowingDetailsBook = true,
                    currentBook = book
                )
            }
        }
    }

    override fun retryLoading() {
        if (_uiState.value is BookshelfUiState.Error) {
            val userGoogleKeyWord = (_uiState.value as BookshelfUiState.Error).userGoogleKeyWord
            getBooksList(userGoogleKeyWord)
        }
    }

    override fun resetHomeScreenStates() {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    isShowingDetailsBook = false,
                    currentBook = null
                )
            }
        }
    }

    override fun updateUserGoogleKeyWord(userGoogleKeyWord: String) {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    userGoogleKeyWord = userGoogleKeyWord
                )
            }
        }
    }

    override fun searchBooks(userGoogleKeyWord: String) {
        getBooksList(userGoogleKeyWord)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}