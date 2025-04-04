package com.dcac.bookshelf.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dcac.bookshelf.R
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.model.VolumeInfo
import com.dcac.bookshelf.utils.toDisplayStringAuthors
import com.dcac.bookshelf.utils.toDisplayStringCategories

@Composable
fun DetailsHomeScreen(
    bookshelfUiState: BookshelfUiState.Success,
    onDetailsHomeScreenAndroidBackPressed: () -> Unit,
    isTest: Boolean = false
) {

    BackHandler {
        onDetailsHomeScreenAndroidBackPressed()
    }

    val currentBook = bookshelfUiState.currentBook as Book
    val context = LocalContext.current
    val bookLink = currentBook.volumeInfo.canonicalVolumeLink
    val bookAuthor = currentBook.volumeInfo.authors.toDisplayStringAuthors()
    val bookCategory = currentBook.volumeInfo.categories.toDisplayStringCategories()
    Log.d("BookDescriptionDetails", "Description: ${currentBook.volumeInfo.description}")
    Log.d("BookObject", currentBook.toString())

    LazyColumn(
        modifier = Modifier.testTag("Details_lazy_column")
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = currentBook.volumeInfo.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                        .semantics { testTag = "bookTitle" }
                )
                Text(
                    text = bookAuthor,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_medium))
                        .semantics { testTag = "bookAuthors" }
                )
                Card(
                    modifier = Modifier
                        .border(
                            width = dimensionResource(R.dimen.padding_x_x_small),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                    shape = RectangleShape
                ) {
                    AsyncImage(
                        model = currentBook.volumeInfo.secureSmallThumbnail ?: R.drawable.broken_image_48,
                        contentDescription = currentBook.volumeInfo.title,
                        placeholder = painterResource(R.drawable.loading_img),
                        error = painterResource(R.drawable.broken_image_48),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                            .semantics { testTag = "bookImage" }
                    )
                }
                Text(
                    text = stringResource(R.string.global_information),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        )
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    val globalInfoListState = rememberLazyListState()
                    val showBackArrow by remember { derivedStateOf { globalInfoListState.canScrollBackward } }
                    val showForwardArrow by remember { derivedStateOf { globalInfoListState.canScrollForward } }

                    LazyRow (state = globalInfoListState,
                        modifier = Modifier.testTag("Global_grid")){
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "startOfGlobalRow" }
                                )
                            }
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.category),
                                text = when {
                                    currentBook.volumeInfo.categories.isNullOrEmpty() -> stringResource(R.string.no_categories)
                                    else -> bookCategory
                                },
                                iconResId = R.drawable.icon_category,
                                contentDescription = "Categories"
                            )
                        }
                        item {
                            IconTextColumn(
                            title = stringResource(R.string.language),
                            text = currentBook.volumeInfo.language,
                            iconResId = R.drawable.icon_translate,
                            contentDescription = "Languages"
                            )
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.type),
                                text = currentBook.volumeInfo.printType?: stringResource(R.string.no_type),
                                iconResId = R.drawable.bookshelf_logo,
                                contentDescription = "Type"
                            )
                        }
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "endOfGlobalRow" }
                                )
                            }
                        }
                    }
                    if(showBackArrow) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_back),
                            contentDescription = stringResource(R.string.scroll_left),
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = dimensionResource(R.dimen.padding_medium))
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "GlobalInformationBackArrow" }
                        )
                    }
                    if (showForwardArrow) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_forward),
                            contentDescription = stringResource(R.string.scroll_right),
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "GlobalInformationForwardArrow" }
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.book_information),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    val bookInfoListState = rememberLazyListState()
                    val showBackArrow by remember { derivedStateOf { bookInfoListState.canScrollBackward } }
                    val showForwardArrow by remember { derivedStateOf { bookInfoListState.canScrollForward } }

                    LazyRow (state = bookInfoListState,
                        modifier = Modifier.testTag("Book_grid")) {
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "startOfBookRow" }
                                )
                            }
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.publisher),
                                text = currentBook.volumeInfo.publisher ?: stringResource(R.string.no_publisher),
                                iconResId = R.drawable.icon_local_library,
                                contentDescription = "Publisher"
                            )
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.published_date),
                                text = currentBook.volumeInfo.publishedDate ?: stringResource(R.string.no_published_date),
                                iconResId = R.drawable.icon_calendar_month,
                                contentDescription = "Published date icon"
                            )
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.page_count),
                                text = when (currentBook.volumeInfo.pageCount) {
                                    null, 0 -> stringResource(R.string.no_page_count)
                                    else -> "${currentBook.volumeInfo.pageCount} pages"
                                },
                                iconResId = R.drawable.icon_auto_stories,
                                contentDescription = "Pages"
                            )
                        }
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "endOfBookRow" }
                                )
                            }
                        }

                    }
                    if (showBackArrow){
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_back),
                            contentDescription = "Scroll left",
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = dimensionResource(R.dimen.padding_medium))
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "BookInformationBackArrow" }
                        )
                    }
                    if(showForwardArrow) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_forward),
                            contentDescription = "Scroll right",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "BookInformationForwardArrow" }
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.availability_information),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    val availabilityInfoListState = rememberLazyListState()
                    val showBackArrow by remember { derivedStateOf { availabilityInfoListState.canScrollBackward } }
                    val showForwardArrow by remember { derivedStateOf { availabilityInfoListState.canScrollForward } }

                    LazyRow(state = availabilityInfoListState,
                        modifier = Modifier.testTag("Availability_grid")) {
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "startOfAvailabilityRow" }
                                )
                            }
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.country),
                                text = currentBook.saleInfo?.country ?: stringResource(R.string.no_country),
                                iconResId = R.drawable.icon_flag,
                                contentDescription = "Country of sale"
                            )
                        }
                        item {
                            IconTextColumn(
                            title = stringResource(R.string.ebook_availability),
                            text = when (currentBook.saleInfo?.isEbook) {
                                true -> stringResource(R.string.ebook_available)
                                false -> stringResource(R.string.ebook_not_available)
                                else -> stringResource(R.string.no_ebook_infos)
                            },
                            iconResId = R.drawable.icon_play_lesson,
                            contentDescription = "Ebook availability"
                            )
                        }
                        item {
                            IconTextColumn(
                                title = stringResource(R.string.retail_price),
                                text = when (val amount = currentBook.saleInfo?.retailPrice?.amount) {
                                    null -> stringResource(R.string.no_retail_price)
                                    else -> "$amount €"
                                },
                                iconResId = R.drawable.icon_price,
                                contentDescription = "Retail price"
                            )
                        }
                        if (isTest) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .size(1.dp)
                                        .semantics { testTag = "endOfAvailabilityRow" }
                                )
                            }
                        }
                    }
                    if(showBackArrow) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_back),
                            contentDescription = "Scroll left",
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = dimensionResource(R.dimen.padding_medium))
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "AvailabilityInformationBackArrow" }
                        )
                    }
                    if(showForwardArrow){
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_forward),
                            contentDescription = "Scroll right",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .semantics { testTag = "AvailabilityInformationForwardArrow" }
                        )
                    }
                }
                Card(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)),
                    shape = RectangleShape,
                    /*colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )*/
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = dimensionResource(R.dimen.padding_x_x_small),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_description),
                            contentDescription = "Book description",
                            modifier = Modifier
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .padding(
                                    start = dimensionResource(R.dimen.padding_small),
                                    end = dimensionResource(R.dimen.padding_medium)
                                )
                                .semantics { testTag = "bookDescriptionIcon" }
                        )
                        Text(
                            text = currentBook.volumeInfo.description ?: stringResource(R.string.no_description),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = dimensionResource(R.dimen.padding_medium),
                                    end = dimensionResource(R.dimen.padding_small),
                                    bottom = dimensionResource(R.dimen.padding_medium)
                                )
                                .semantics { testTag = "bookDescription" }
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .testTag("bookLinkCard")
                        .fillMaxWidth()
                        .clickable {
                            bookLink?.let {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                context.startActivity(intent)
                            }
                        },
                    shape = RectangleShape,
                    /*colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )*/
                ) {
                    Row(
                        modifier = Modifier
                            .border(
                                width = dimensionResource(R.dimen.padding_x_x_small),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_share),
                            contentDescription = "Book link",
                            modifier = Modifier
                                .size(dimensionResource(R.dimen.fixed_icon_size))
                                .padding(
                                    start = dimensionResource(R.dimen.padding_small),
                                    end = dimensionResource(R.dimen.padding_medium)
                                )
                                .semantics { testTag = "bookLinkIcon" }
                        )
                        Text(
                            text = bookLink ?: stringResource(R.string.no_book_link),
                            style = MaterialTheme.typography.bodyLarge,
                            textDecoration = TextDecoration.Underline,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = dimensionResource(R.dimen.padding_medium),
                                    end = dimensionResource(R.dimen.padding_small),
                                    bottom = dimensionResource(R.dimen.padding_medium)
                                )
                                .semantics { testTag = "bookLink" }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconTextColumn(
    title: String,
    text: String,
    iconResId: Int,
    contentDescription: String
) {
    Card(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_x_small)),
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier
                .height(dimensionResource(R.dimen.fixed_height_cells_size))
                .width(dimensionResource(R.dimen.fixed_width_cells_size))
                .border(
                    width = dimensionResource(R.dimen.padding_x_x_small),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_small)
                    )
                    .semantics { testTag = "iconTextColumnTitle_$title" }
            )
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.fixed_icon_size))
                    .semantics { testTag = "iconTextColumnIcon_$contentDescription" }
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .semantics { testTag = "iconTextColumnText_$text" }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
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

    DetailsHomeScreen(
        bookshelfUiState = BookshelfUiState.Success(booksList = sampleBooks, isShowingDetailsBook = true, userGoogleKeyWord = ""),
        onDetailsHomeScreenAndroidBackPressed = {}
    )

}