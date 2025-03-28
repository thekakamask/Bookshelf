package com.dcac.bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dcac.bookshelf.R
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState

@Composable
fun LoadingHomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(R.dimen.padding_x_large),
                horizontal = dimensionResource(R.dimen.padding_medium)
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp)
        )
    }
}

@Composable
fun ErrorHomeScreen(
    bookshelfUiState: BookshelfUiState.Error,
    onRetryClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(
            text = bookshelfUiState.message,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
        )
        Button(onClick = onRetryClick) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun SuccessHomeScreen(
    bookshelfUiState: BookshelfUiState.Success,
    onBookClick: (Book) -> Unit,
    userGoogleKeyWord: String = "",
    onUserGoogleKeyWordChange: (String) -> Unit = {},
    onKeyboardDone: (String) -> Unit = {}
) {
    var tempGoogleKeyWord by remember { mutableStateOf(userGoogleKeyWord) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
    ){
        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_x_small))
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            value = tempGoogleKeyWord,
            onValueChange = { tempGoogleKeyWord = it },
            singleLine = true,
            label = {
                Text(
                    text = stringResource(R.string.keyword_search),
                    style = MaterialTheme.typography.titleSmall
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onKeyboardDone(tempGoogleKeyWord)
                    onUserGoogleKeyWordChange(tempGoogleKeyWord)
                }
            )
        )
        Log.d("SuccessHomeScreen", "booksList: ${bookshelfUiState.booksList}")
        if (bookshelfUiState.booksList.isEmpty()) {
            InitialMessage()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(
                    items = bookshelfUiState.booksList,
                    key = { it.id }
                ) { book ->
                    BookCard(
                        book = book,
                        onBookClick = onBookClick
                    )
                }
            }
        }
    }
}

@Composable
fun InitialMessage(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.welcome_user),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = stringResource(R.string.enter_keywords),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        Image(
            painter = painterResource(id = R.drawable.bookshelf_logo),
            contentDescription = "bookshelf_initial_logo",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun BookCard(
    book: Book,
    onBookClick: (Book) -> Unit
) {

    Card(
        shape = RectangleShape,
        modifier = Modifier
            .border(
                width = dimensionResource(R.dimen.padding_x_x_small),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            .clickable { onBookClick(book) }
    ) {
        AsyncImage(
            model = book.volumeInfo.secureThumbnail ?: book.volumeInfo.secureSmallThumbnail ?: R.drawable.broken_image_48,
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
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {

}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorHomeScreen(
        bookshelfUiState = BookshelfUiState.Error("An error occurred"),
        onRetryClick = {}
    )

}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingHomeScreen()
}