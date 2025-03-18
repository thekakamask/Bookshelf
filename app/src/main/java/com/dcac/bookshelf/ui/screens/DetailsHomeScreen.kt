package com.dcac.bookshelf.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.dcac.bookshelf.R
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.utils.toDisplayString

@Composable
fun DetailsHomeScreen(
    bookshelfUiState: BookshelfUiState.Success,
    onDetailsHomeScreenAndroidBackPressed: () -> Unit
) {

    BackHandler {
        onDetailsHomeScreenAndroidBackPressed()
    }

    val currentBook = bookshelfUiState.currentBook as Book
    Log.d("BookDescriptionDetails", "Description: ${currentBook.volumeInfo.description}")
    Log.d("BookObject", currentBook.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Text(
            text = currentBook.volumeInfo.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = currentBook.volumeInfo.authors.toDisplayString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )

        AsyncImage(
            model = currentBook.volumeInfo.secureSmallThumbnail ?: R.drawable.broken_image_48,
            contentDescription = currentBook.volumeInfo.title,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.broken_image_48),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(MaterialTheme.shapes.large)
        )

        Text(
            text = currentBook.volumeInfo.description ?: stringResource(R.string.no_description),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}