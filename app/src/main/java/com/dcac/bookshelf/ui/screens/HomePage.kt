package com.dcac.bookshelf.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dcac.bookshelf.R
import com.dcac.bookshelf.model.BookshelfUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    val bookshelfUiState = bookshelfViewModel.uiState.collectAsState().value

    val scrollBehavior = if (bookshelfUiState is BookshelfUiState.Success && bookshelfUiState.isShowingDetailsBook) {
        null
    } else {
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    }

    Scaffold(
        modifier = scrollBehavior?.let { Modifier.nestedScroll(it.nestedScrollConnection) } ?: Modifier,
        topBar =  {
            BookshelfTopBar(
                bookshelfUiState = bookshelfUiState,
                scrollBehavior = scrollBehavior,
                onBackArrowClick = { bookshelfViewModel.resetHomeScreenStates() },
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            AnimatedContent(
                targetState = bookshelfUiState,
                label = "AnimatedContent",
                transitionSpec = {
                    when {
                        targetState is BookshelfUiState.Success && (targetState as BookshelfUiState.Success).isShowingDetailsBook -> {
                            slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(500)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(500)
                            )
                        }
                        initialState is BookshelfUiState.Success && (initialState as BookshelfUiState.Success).isShowingDetailsBook -> {
                            slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(500)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { it },
                                animationSpec = tween(500)
                            )
                        }
                        else -> ContentTransform(
                            initialContentExit = fadeOut(animationSpec = tween(500)),
                            targetContentEnter = fadeIn(animationSpec = tween(500))
                        ) //No transition
                    }
                }
            ) { state ->
                when (state) {
                    is BookshelfUiState.Loading -> LoadingHomeScreen()

                    is BookshelfUiState.Error -> ErrorHomeScreen(
                        bookshelfUiState = state,
                        onRetryClick = { bookshelfViewModel.retryLoading()}
                    )

                    is BookshelfUiState.Success -> {
                        if (state.isShowingDetailsBook) {
                            DetailsHomeScreen(
                                bookshelfUiState = state,
                                onDetailsHomeScreenAndroidBackPressed = { bookshelfViewModel.resetHomeScreenStates() }
                            )
                        } else {
                            SuccessHomeScreen(
                                bookshelfUiState = state,
                                onBookClick = { book ->
                                    bookshelfViewModel.updateCurrentBook(book)
                                },
                                userGoogleKeyWord = state.userGoogleKeyWord,
                                onUserGoogleKeyWordChange = {
                                    bookshelfViewModel.updateUserGoogleKeyWord(it)
                                },
                                onKeyboardDone = {
                                    bookshelfViewModel.searchBooks(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopBar(
    bookshelfUiState: BookshelfUiState,
    scrollBehavior: TopAppBarScrollBehavior?,
    onBackArrowClick: () -> Unit,
){
    Column {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    text = when (bookshelfUiState) {
                        is BookshelfUiState.Success -> stringResource(R.string.app_name)
                        is BookshelfUiState.Loading -> stringResource(R.string.app_name_loading)
                        else -> stringResource(R.string.app_name_error)
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            navigationIcon = {
                if (bookshelfUiState is BookshelfUiState.Success && bookshelfUiState.isShowingDetailsBook) {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }
}