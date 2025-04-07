package com.dcac.bookshelf

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.dcac.bookshelf.fakeDataAndUtils.FakeBookshelfViewModel
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeDetailsSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeErrorBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeGridSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeInitialSuccessBookshelfUiState
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeLoadingBookshelfUiState
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.rules.onNodeWithContentDescriptionForStringId
import com.dcac.bookshelf.rules.onNodeWithStringId
import com.dcac.bookshelf.ui.screens.BookshelfTopBar
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test


class UiAppBarScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun appbarSuccessHomeScreen_verifyContent() {
        composeTestRule.setContent { 
            BookshelfTopBar(
                bookshelfUiState = fakeInitialSuccessBookshelfUiState,
                scrollBehavior = null,
                onBackArrowClick = {}
            )
        }

        composeTestRule.onNodeWithStringId(R.string.app_name)
            .assertExists()
            .assertIsDisplayed()

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun appbarSuccessDetailsScreen_verifyContentAndBehavior() {
        val viewModel = FakeBookshelfViewModel(fakeDetailsSuccessBookshelfUiState)
        val keyword = "Jazz"

        composeTestRule.setContent {
            BookshelfTopBar(
                bookshelfUiState = fakeDetailsSuccessBookshelfUiState,
                scrollBehavior = null,
                onBackArrowClick = { viewModel.resetHomeScreenStates() }
            )
        }

        composeTestRule.onNodeWithStringId(R.string.app_name)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back_button)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assert(viewModel.resetHomeScreenStatesCalled)

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun appbarLoading_verifyContent() {
        composeTestRule.setContent {
            BookshelfTopBar(
                bookshelfUiState = fakeLoadingBookshelfUiState,
                scrollBehavior = null,
                onBackArrowClick = {}
            )
        }
        composeTestRule.onNodeWithStringId(R.string.app_name_loading)
            .assertExists()
            .assertIsDisplayed()

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun appbarError_verifyContent() {
        composeTestRule.setContent {
            BookshelfTopBar(
                bookshelfUiState = fakeErrorBookshelfUiState,
                scrollBehavior = null,
                onBackArrowClick = {}
            )
        }

        composeTestRule.onNodeWithStringId(R.string.app_name_error)
            .assertExists()
            .assertIsDisplayed()

    }

}