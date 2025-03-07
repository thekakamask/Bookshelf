package com.dcac.bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.dcac.bookshelf.R
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.model.VolumeInfo

@Composable
fun LoadingScreen() {

}

@Composable
fun ErrorScreen() {

}

@Composable
fun GridScreen(
    bookshelfUiState: BookshelfUiState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_x_small)),
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(
                items = bookshelfUiState.booksList,
                key = { it.id }
            ) { book ->
                BookCard(
                    book = book,
                )
            }
        }
    }
}

@Composable
fun BookCard(
    book: Book
) {

    Card(
        shape = RectangleShape,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_x_small))
    ) {
        AsyncImage(
            model = book.volumeInfo.secureThumbnail ?: R.drawable.broken_image_48,
            contentDescription = book.id,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.broken_image_48),
            contentScale = ContentScale.Crop,
            onError = { error ->
                Log.e("BookCard", "Image loading error: $error")
                Log.e("BookCard", "Error details: ${error.result}")
            },
            modifier = Modifier
                .aspectRatio(0.5f)
        )
        /*AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.volumeInfo.imageLinks?.thumbnail ?: R.drawable.broken_image_48)
                .listener(
                    onError = { _, throwable ->
                        Log.e("BookCard", "Erreur Coil", throwable) // Log automatiquement le stacktrace
                        Log.e("BookCard", "Erreur Coil: ${throwable.localizedMessage ?: "Message inconnu"}")
                    },
                    onSuccess = { _, _ ->
                        Log.d("BookCard", "Image chargée avec succès: ${book.volumeInfo.imageLinks?.thumbnail}")
                    }
                )
                .build(),
            contentDescription = book.id,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.broken_image_48),
            contentScale = ContentScale.Crop,
            imageLoader = ImageLoaderSingleton.imageLoader,
            modifier = Modifier.aspectRatio(0.5f)
        )*/

    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    val sampleBooks = listOf(
        Book(
            id = "1",
            selfLink = "",
            volumeInfo = VolumeInfo(
                title = "Sample Book",
                authors = listOf("Author Name"),
                publisher = "Sample Publisher",
                publishedDate = "2024",
                description = "This is a sample description.",
                pageCount = 100,
                categories = listOf("Fiction"),
                imageLinks = null,
                industryIdentifiers = emptyList(),
                language = "en"
            ),
            saleInfo = null,
            accessInfo = null
        ),
        Book(
            id = "2",
            selfLink = "",
            volumeInfo = VolumeInfo(
                title = "Sample Book 2",
                authors = listOf("Author Name 2"),
                publisher = "Sample Publisher 2",
                publishedDate = "2024",
                description = "This is a sample description 2.",
                pageCount = 100,
                categories = listOf("Fiction"),
                imageLinks = null,
                industryIdentifiers = emptyList(),
                language = "en"
            ),
            saleInfo = null,
            accessInfo = null
        )
    )

    GridScreen(
        bookshelfUiState = BookshelfUiState.Success(booksList = sampleBooks, isShowingDetailsBook = false)
    )
}
