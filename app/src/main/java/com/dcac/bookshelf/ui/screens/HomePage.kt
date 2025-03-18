package com.dcac.bookshelf.ui.screens

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
            when (bookshelfUiState) {
                is BookshelfUiState.Loading -> LoadingHomeScreen()
                is BookshelfUiState.Success -> {
                   if (bookshelfUiState.isShowingDetailsBook) {
                       DetailsHomeScreen(
                           bookshelfUiState = bookshelfUiState,
                           onDetailsHomeScreenAndroidBackPressed = { bookshelfViewModel.resetHomeScreenStates() }
                       )
                   } else {
                       GridScreen(
                           bookshelfUiState = bookshelfUiState,
                           onBookClick = { book ->
                               bookshelfViewModel.updateCurrentBook(book)
                           }
                       )
                   }
                }
                is BookshelfUiState.Error -> ErrorHomeScreen(
                    bookshelfUiState = bookshelfUiState,
                    onRetryClick = { bookshelfViewModel.retryLoading() }
                )
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
                if (bookshelfUiState is BookshelfUiState.Success) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                } else if (bookshelfUiState is BookshelfUiState.Loading) {
                    Text(
                        text = stringResource(R.string.app_name_loading),
                        style = MaterialTheme.typography.headlineMedium
                    )
                } else {
                    Text(
                        text = stringResource(R.string.app_name_error),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
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