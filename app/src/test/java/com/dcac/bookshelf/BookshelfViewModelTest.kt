package com.dcac.bookshelf

import app.cash.turbine.test
import com.dcac.bookshelf.fakeDataAndUtils.FakeDataSource
import com.dcac.bookshelf.fakeDataAndUtils.FakeErrorRepository
import com.dcac.bookshelf.fakeDataAndUtils.FakeRepository
import com.dcac.bookshelf.rules.TestDispatcherRule
import com.dcac.bookshelf.ui.screens.BookshelfViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_updateCurrentBook_verifyBookshelfUiStateSuccess() = runTest {
        val viewModel = BookshelfViewModel(FakeRepository())

        // Simulate Search
        viewModel.searchBooks("jazz")
        advanceUntilIdle()

        // Choose book
        val book = FakeDataSource.bookList.items!!.first()
        viewModel.updateCurrentBook(book)

        assertEquals(
            FakeDataSource.fakeSuccessBookshelfUiStateAfterBookClick,
            viewModel.uiState.value
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_retryLoading_afterError_emitsLoadingThenSuccess() = runTest {
        val repository = FakeErrorRepository(shouldFail = true)
        val viewModel = BookshelfViewModel(repository)

        viewModel.uiState.test {
            // Init state
            assertEquals(FakeDataSource.fakeSuccessBookshelfUiStateAfterInit, awaitItem())

            // First search -> error
            viewModel.searchBooks("jazz")
            advanceUntilIdle()
            assertEquals(FakeDataSource.fakeLoadingBookshelfUiState, awaitItem())
            assertEquals(FakeDataSource.fakeErrorBookshelfUiState, awaitItem())

            // Now switch to success mode
            repository.setShouldFail(false)

            // Retry
            viewModel.retryLoading()
            assertEquals(FakeDataSource.fakeLoadingBookshelfUiState, awaitItem())
            assertEquals(FakeDataSource.fakeSuccessBookshelfUiStateAfterRetry, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_retryLoading_verifyBookshelfUiStateError() =
        runTest {
            val viewModel = BookshelfViewModel(
                bookshelfRepository = FakeErrorRepository(shouldFail = true)
            )
            viewModel.searchBooks("jazz")
            advanceUntilIdle()

            viewModel.retryLoading()
            assertEquals(
                FakeDataSource.fakeErrorBookshelfUiState,
                viewModel.uiState.value
            )

        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_resetHomeScreenStates_verifyBookshelfUiStateSuccess() =
        runTest {
            val viewModel = BookshelfViewModel(
                bookshelfRepository = FakeRepository()
            )

            viewModel.searchBooks("jazz")
            advanceUntilIdle()
            viewModel.updateCurrentBook(FakeDataSource.bookList.items!!.first())

            viewModel.resetHomeScreenStates()
            assertEquals(
                FakeDataSource.fakeSuccessBookshelfUiStateAfterReset,
                viewModel.uiState.value
            )

        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_updateUserGoogleKeyWord_verifyBookshelfUiStateSuccess() =
        runTest {
            val viewModel = BookshelfViewModel(
                bookshelfRepository = FakeRepository()
            )

            // Simulate Search
            viewModel.searchBooks("jazz")
            advanceUntilIdle()


            viewModel.updateUserGoogleKeyWord("jazz")
            assertEquals(
                FakeDataSource.fakeSuccessBookshelfUiStateAfterUpdateUser,
                viewModel.uiState.value
            )

        }

    @Test
    fun bookshelfViewModel_searchBooks_emitsInitialSuccessThenLoadingThenSuccess() = runTest {
        val viewModel = BookshelfViewModel(FakeRepository())

        viewModel.uiState.test {
            //INITIAL STATE
            assertEquals(FakeDataSource.fakeSuccessBookshelfUiStateAfterInit, awaitItem())

            viewModel.searchBooks("jazz")

            // LOADING STATE
            assertEquals(FakeDataSource.fakeLoadingBookshelfUiState, awaitItem())

            // SUCCESS STATE AFTER RESULT
            assertEquals(FakeDataSource.fakeSuccessBookshelfUiStateAfterSearch, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bookshelfViewModel_searchBooks_verifyBookshelfUiStateError() = runTest {
        val viewModel = BookshelfViewModel(
            bookshelfRepository = FakeErrorRepository(shouldFail = true)
        )

        viewModel.searchBooks("jazz")
        advanceUntilIdle()

        assertEquals(
            FakeDataSource.fakeErrorBookshelfUiState,
            viewModel.uiState.value
        )
    }

}