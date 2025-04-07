package com.dcac.bookshelf

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import com.dcac.bookshelf.fakeDataAndUtils.FakeBookshelfViewModel
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeDetailsSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeErrorBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeGridSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeInitialSuccessBookshelfUiState
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.rules.onAllNodesWithTagPrefix
import com.dcac.bookshelf.rules.onNodeWithContentDescriptionForStringId
import com.dcac.bookshelf.rules.onNodeWithStringId
import com.dcac.bookshelf.rules.onNodeWithTagForStringId
import com.dcac.bookshelf.rules.onNodeWithTagValue
import com.dcac.bookshelf.ui.screens.ErrorHomeScreen
import com.dcac.bookshelf.ui.screens.LoadingHomeScreen
import com.dcac.bookshelf.ui.screens.SuccessHomeScreen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class UiHomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_successStateInitial_verifyContentAndBehavior() {
        val viewModel = FakeBookshelfViewModel(fakeInitialSuccessBookshelfUiState)
        val keyword = "Jazz"

        composeTestRule.setContent {
            SuccessHomeScreen(
                bookshelfUiState = fakeInitialSuccessBookshelfUiState,
                onBookClick = {},
                userGoogleKeyWord = "",
                onUserGoogleKeyWordChange = { viewModel.updateUserGoogleKeyWord(it) },
                onKeyboardDone = { viewModel.searchBooks(it) }
            )
        }

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.welcome_logo)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.welcome_user)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.enter_keywords)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTagForStringId(R.string.outline_text_field)
            .assertExists()
            .assertIsDisplayed()
            .performTextInput(keyword)

        composeTestRule.onNodeWithTagForStringId(R.string.outline_text_field)
            .assertTextContains("Jazz")
            .performImeAction()

        composeTestRule.runOnIdle {
            assert(viewModel.updateUserGoogleKeyWordCalled)
            assert(viewModel.searchBooksCalled)

            val currentUiState = viewModel.uiState.value

            assert(currentUiState is BookshelfUiState.Success)
            assert(currentUiState === fakeGridSuccessBookshelfUiState)

            val currentBooksList = (currentUiState as BookshelfUiState.Success).booksList
            val currentKeyword = currentUiState.userGoogleKeyWord

            assertNotNull(currentBooksList)
            assertNotNull(currentKeyword)
            assertEquals(5, currentBooksList.size)
            assertEquals(keyword, currentKeyword)

        }

    }

    @Test
    fun homeScreen_successStateGrid_verifyContentAndBehavior() {
        val viewModel = FakeBookshelfViewModel(fakeGridSuccessBookshelfUiState)
        val bookIds = fakeGridSuccessBookshelfUiState.booksList.map { it.id }
        val keyword = "Jazz"

        composeTestRule.setContent {
            SuccessHomeScreen(
                bookshelfUiState = fakeGridSuccessBookshelfUiState,
                onBookClick = { viewModel.updateCurrentBook(it) },
                userGoogleKeyWord = fakeGridSuccessBookshelfUiState.userGoogleKeyWord,
                onUserGoogleKeyWordChange = { viewModel.updateUserGoogleKeyWord(it) },
                onKeyboardDone = { viewModel.searchBooks(it) }
            )
        }

        composeTestRule.onNodeWithTagForStringId(R.string.outline_text_field)
            .assertExists()
            .assertIsDisplayed()
            .assertTextContains(keyword)


        composeTestRule.onNodeWithTagValue("grid")
            .performScrollToNode(hasTestTag("bookCard_4"))

        composeTestRule.waitForIdle()

        composeTestRule.onAllNodesWithTagPrefix("bookCard_")
            .assertCountEquals(5)

        bookIds.forEach { id ->
            composeTestRule.onNodeWithTagValue("bookImage_$id", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
        }

        composeTestRule.onNodeWithTagValue("grid")
            .performScrollToNode(hasTestTag("bookCard_0"))

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTagValue("bookCard_0")
            .performClick()

        composeTestRule.runOnIdle {
            assert(viewModel.updateCurrentBookCalled)

            val currentUiState = viewModel.uiState.value

            assert(currentUiState is BookshelfUiState.Success)
            assert(currentUiState === fakeDetailsSuccessBookshelfUiState)

            val currentBookList = (currentUiState as BookshelfUiState.Success).booksList
            val currentBook = currentUiState.currentBook
            val currentShowingDetailsBook = currentUiState.isShowingDetailsBook

            assertNotNull(currentBookList)
            assertTrue(currentShowingDetailsBook)
            assertNotNull(currentBook)
            assertEquals("0", currentBook?.id)
        }
    }



    @Test
    fun homeScreen_errorState_verifyContentAndBehavior() {
        val viewModel = FakeBookshelfViewModel(fakeErrorBookshelfUiState)
        val keyword = "Jazz"

        composeTestRule.setContent {
            ErrorHomeScreen(
                bookshelfUiState = fakeErrorBookshelfUiState,
                onRetryClick = { viewModel.retryLoading() }
            )
        }

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.image_error)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTagForStringId(R.string.error_text)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.retry)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assert(viewModel.retryLoadingCalled)

        val currentUiState = viewModel.uiState.value

        assert(currentUiState is BookshelfUiState.Success)
        assert(currentUiState === fakeGridSuccessBookshelfUiState)

        val currentBooksList = (currentUiState as BookshelfUiState.Success).booksList
        val currentKeyword = currentUiState.userGoogleKeyWord

        assertNotNull(currentBooksList)
        assertNotNull(currentKeyword)
        assertEquals(5, currentBooksList.size)
        assertEquals(keyword, currentKeyword)
    }

    @Test
    fun homeScreen_loadingState_verifyContent() {
        composeTestRule.setContent {
            LoadingHomeScreen()
        }

        composeTestRule.onNodeWithTagForStringId(R.string.loading_indicator)
            .assertExists()
            .assertIsDisplayed()
    }

}