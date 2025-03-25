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
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookshelfUiState>(BookshelfUiState.Loading)
    val uiState: StateFlow<BookshelfUiState> = _uiState.asStateFlow()

    init {
        initUiState()
    }

    private fun initUiState() {
        viewModelScope.launch {
            _uiState.value = BookshelfUiState.Loading
            try {
                _uiState.value = BookshelfUiState.Success(
                    userGoogleKeyWord = "",
                    booksList = emptyList()
                )
            } catch (e: IOException) {
                _uiState.value = BookshelfUiState.Error("Network error, please check your connection.")
                println("❌ Network error: ${e.message}")
            } catch (e: HttpException) {
                _uiState.value = BookshelfUiState.Error("Server error: ${e.code()}")
                println("❌ HTTP error: ${e.code()}")
            } catch (e: Exception) {
                _uiState.value = BookshelfUiState.Error("Unexpected error: ${e.message}")
                println("❌ Unexpected error: ${e.message}")
            }
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
                _uiState.value = BookshelfUiState.Error("Network error, please check your connection.")
                println("❌ Network error: ${e.message}")
            } catch (e: HttpException) {
                _uiState.value = BookshelfUiState.Error("Server error: ${e.code()}")
                println("❌ HTTP error: ${e.code()}")
            } catch (e: Exception) {
                _uiState.value = BookshelfUiState.Error("Unexpected error: ${e.message}")
                println("❌ Unexpected error: ${e.message}")
            }
        }
    }

    fun updateCurrentBook(book: Book) {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    isShowingDetailsBook = true,
                    currentBook = book
                )
            }
        }
    }

    fun retryLoading() {
        if (_uiState.value is BookshelfUiState.Success) {
            getBooksList((_uiState.value as BookshelfUiState.Success).userGoogleKeyWord)
        }
    }

    fun resetHomeScreenStates() {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    isShowingDetailsBook = false,
                    currentBook = null
                )
            }
        }
    }

    fun updateUserGoogleKeyWord(userGoogleKeyWord: String) {
        if (_uiState.value is BookshelfUiState.Success) {
            _uiState.update {
                (it as BookshelfUiState.Success).copy(
                    userGoogleKeyWord = userGoogleKeyWord
                )
            }
        }
    }

    fun searchBooks(userGoogleKeyWord: String) {
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