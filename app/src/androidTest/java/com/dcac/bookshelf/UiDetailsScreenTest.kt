package com.dcac.bookshelf


import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performScrollToNode
import com.dcac.bookshelf.fakeDataAndUtils.FakeBookshelfViewModel
import com.dcac.bookshelf.fakeDataAndUtils.FakeData.fakeDetailsSuccessBookshelfUiState
import com.dcac.bookshelf.ui.screens.DetailsHomeScreen
import org.junit.Rule
import org.junit.Test

class UiDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailsScreen_verifyContentAndBehavior() {
        val viewModel = FakeBookshelfViewModel(fakeDetailsSuccessBookshelfUiState)
        val currentBook = fakeDetailsSuccessBookshelfUiState.currentBook

        composeTestRule.setContent {
            DetailsHomeScreen(
                bookshelfUiState = fakeDetailsSuccessBookshelfUiState,
                onDetailsHomeScreenAndroidBackPressed = { viewModel.resetHomeScreenStates() },
                isTest = true
            )
        }


        if (currentBook != null) {
            composeTestRule.onNodeWithTagValue("bookTitle")
                .assertExists()
                .assertTextEquals(currentBook.volumeInfo.title.trim())

            val expectedAuthors =  if (currentBook.volumeInfo.authors.isNullOrEmpty()) {
                composeTestRule.activity.getString(R.string.no_authors)
            } else {
                currentBook.volumeInfo.authors!!.joinToString(", ")
            }

            composeTestRule.onNodeWithTagValue("bookAuthors")
                .assertExists()
                .assertTextEquals(expectedAuthors.trim())

            val expectedImageDescription = currentBook.volumeInfo.title
            composeTestRule.onNodeWithTagValue("bookImage")
                .assertExists()
                .assertIsDisplayed()
                .assertContentDescriptionEquals(expectedImageDescription)

            composeTestRule.onNodeWithStringId(R.string.global_information)
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Category")
                .assertExists()
                .assertTextEquals("Category")

            val expectedCategoryText = if (currentBook.volumeInfo.categories.isNullOrEmpty()) {
                composeTestRule.activity.getString(R.string.no_categories)
            } else {
                currentBook.volumeInfo.categories!!.joinToString(", ")
            }

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedCategoryText")
                .assertExists()
                .assertTextEquals(expectedCategoryText.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Categories")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("GlobalInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("GlobalInformationBackArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Language")
                .assertExists()
                .assertTextEquals("Language")

            val expectedLanguageText = currentBook.volumeInfo.language

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedLanguageText")
                .assertExists()
                .assertTextEquals(expectedLanguageText.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Languages")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Global_grid")
                .performScrollToNode(hasTestTag("endOfGlobalRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("GlobalInformationBackArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("GlobalInformationForwardArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Type")
                .assertExists()
                .assertTextEquals("Type")

            val expectedType = currentBook.volumeInfo.printType ?: R.string.type.toString()

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedType")
                .assertExists()
                .assertTextEquals(expectedType.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Type")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Global_grid")
                .performScrollToNode(hasTestTag("startOfGlobalRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("GlobalInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("GlobalInformationBackArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithStringId(R.string.book_information)
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Publisher")
                .assertExists()
                .assertTextEquals("Publisher")

            val expectedPublisher = currentBook.volumeInfo.publisher ?: R.string.publisher.toString()

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedPublisher")
                .assertExists()
                .assertTextEquals(expectedPublisher.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Publisher")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("BookInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("BookInformationBackArrow")
                .assertDoesNotExist()

            val expectedPublishedDate = currentBook.volumeInfo.publishedDate ?: R.string.published_date.toString()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Published date")
                .assertExists()
                .assertTextEquals("Published date")

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedPublishedDate")
                .assertExists()
                .assertTextEquals(expectedPublishedDate.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Published date icon")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Book_grid")
                .performScrollToNode(hasTestTag("endOfBookRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("BookInformationBackArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("BookInformationForwardArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Page count")
                .assertExists()
                .assertTextEquals("Page count")

            val expectedPageCount = when (currentBook.volumeInfo.pageCount) {
                null, 0 -> R.string.no_page_count.toString()
                else -> "${currentBook.volumeInfo.pageCount} pages"
            }

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedPageCount")
                .assertExists()
                .assertTextEquals(expectedPageCount.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Pages")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Book_grid")
                .performScrollToNode(hasTestTag("startOfBookRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("BookInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("BookInformationBackArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithStringId(R.string.availability_information)
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Country of sale")
                .assertExists()
                .assertTextEquals("Country of sale")

            val expectedCountry = currentBook.saleInfo?.country ?: R.string.no_country.toString()

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedCountry")
                .assertExists()
                .assertTextEquals(expectedCountry.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Country of sale")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationBackArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Ebook availability")
                .assertExists()
                .assertTextEquals("Ebook availability")

            val expectedEbookAvailability = when (currentBook.saleInfo?.isEbook) {
                true -> composeTestRule.activity.getString(R.string.ebook_available)
                false -> composeTestRule.activity.getString(R.string.ebook_not_available)
                else -> composeTestRule.activity.getString(R.string.no_ebook_infos)
            }

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedEbookAvailability")
                .assertExists()
                .assertTextEquals(expectedEbookAvailability.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Ebook availability")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Availability_grid")
                .performScrollToNode(hasTestTag("endOfAvailabilityRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationBackArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationForwardArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("iconTextColumnTitle_Retail price")
                .assertExists()
                .assertTextEquals("Retail price")

            val expectedRetailPrice = when (val amount = currentBook.saleInfo?.retailPrice?.amount) {
                null -> composeTestRule.activity.getString(R.string.no_retail_price)
                else -> "$amount €"
            }

            composeTestRule.onNodeWithTagValue("iconTextColumnText_$expectedRetailPrice")
                .assertExists()
                .assertTextEquals(expectedRetailPrice.trim())

            composeTestRule.onNodeWithTagValue("iconTextColumnIcon_Retail price")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("Availability_grid")
                .performScrollToNode(hasTestTag("startOfAvailabilityRow"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationForwardArrow")
                .assertExists()
                .assertIsDisplayed()

            composeTestRule.onNodeWithTagValue("AvailabilityInformationBackArrow")
                .assertDoesNotExist()

            composeTestRule.onNodeWithTagValue("Details_lazy_column")
                .performScrollToNode(hasTestTag("bookDescription"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("bookDescriptionIcon")
                .assertExists()
                .assertIsDisplayed()
                .assertContentDescriptionEquals("Book description")

            val expectedDescription = currentBook.volumeInfo.description ?: R.string.no_description.toString()

            composeTestRule.onNodeWithTagValue("bookDescription")
                .assertExists()
                .assertIsDisplayed()
                .assertTextEquals(expectedDescription.trim())

            composeTestRule.onNodeWithTagValue("Details_lazy_column")
                .performScrollToNode(hasTestTag("bookLinkCard"))

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTagValue("bookLinkIcon", true)
                .assertExists()
                .assertIsDisplayed()
                .assertContentDescriptionEquals("Book link")

            val expectedBookLink = currentBook.volumeInfo.canonicalVolumeLink ?: R.string.no_book_link.toString()

            composeTestRule.onNodeWithTagValue("bookLink", true)
                .assertExists()
                .assertIsDisplayed()
                .assertTextEquals(expectedBookLink.trim())

        }
    }
}
