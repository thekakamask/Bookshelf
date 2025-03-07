package com.dcac.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (bookshelfUiState) {
                is BookshelfUiState.Loading -> LoadingScreen()
                is BookshelfUiState.Success -> GridScreen(bookshelfUiState = bookshelfUiState)
                is BookshelfUiState.Error -> ErrorScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopBar(
    scrollBehavior: TopAppBarScrollBehavior?
){
    Column {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        )
    }

}