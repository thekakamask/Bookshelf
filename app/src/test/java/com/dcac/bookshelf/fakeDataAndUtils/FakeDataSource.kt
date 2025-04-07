package com.dcac.bookshelf.fakeDataAndUtils

import androidx.compose.runtime.Immutable
import com.dcac.bookshelf.model.AccessInfo
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BooksList
import com.dcac.bookshelf.model.BookshelfUiState
import com.dcac.bookshelf.model.PriceInfo
import com.dcac.bookshelf.model.SaleInfo
import com.dcac.bookshelf.model.VolumeInfo

object FakeDataSource {

    private val fakeDataBook = listOf(
        Book(
            id = "0",
            volumeInfo = VolumeInfo(
                title = "The History of Jazz",
                authors = listOf("Ted Gioia"),
                publisher = "Oxford University Press",
                publishedDate = "2011-05-09",
                description = "A panoramic history of the genre brings to life the diverse places in which jazz evolved, traces the origins of its various styles, and offers commentary on the music itself.",
                pageCount = 453,
                printType = "BOOK",
                categories = listOf("Music"),
                maturityRating = "NOT_MATURE",
                language = "en",
                canonicalVolumeLink = "https://books.google.com/books/about/The_History_of_Jazz.html?hl=&id=ubERDAAAQBAJ"
            ),
            saleInfo = SaleInfo(
                country = "FR",
                saleability = "NOT_FOR_SALE",
                isEbook = false
            ),
            accessInfo = AccessInfo(
                country = "FR",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED"
            )
        ),
        Book(
            id = "1",
            volumeInfo = VolumeInfo(
                title = "Historical Dictionary of Jazz",
                authors = listOf("John S. Davis"),
                publisher = "Rowman & Littlefield",
                publishedDate = "2020-09-15",
                description = "Jazz is a music born in the United States and formed by a combination of influences. In its infancy, jazz was a melting pot of military brass bands, work songs and field hollers of the United States slaves during the 19th century, European harmonies and forms, and the rhythms of Africa and the Caribbean. Later, the blues and the influence of Spanish and French Creoles with European classical training nudged jazz further along in its development. As it moved through the swing era of the 1930s, bebop of the 1940s, and cool jazz of the 1950s, jazz continued to serve as a reflection of societal changes. During the turbulent 1960s, freedom and unrest were expressed through Free Jazz and the Avant Garde. Popular and world music have been incorporated and continue to expand the impact and reach of jazz. Today, jazz is truly an international art form. This second edition of Historical Dictionary of Jazz contains a chronology, an introduction, and an extensive bibliography. The dictionary section has over 1,500 cross-referenced entries on musicians, styles of jazz, instruments, recording labels, bands and band leaders, and more. This book is an excellent resource for students, researchers, and anyone wanting to know more about Jazz.",
                pageCount = 559,
                printType = "BOOK",
                categories = listOf("Music"),
                maturityRating = "NOT_MATURE",
                language = "en",
                canonicalVolumeLink = "https://play.google.com/store/books/details?id=S_r1DwAAQBAJ"
            ),
            saleInfo = SaleInfo(
                country = "FR",
                saleability = "FOR_SALE",
                isEbook = true,
                listPrice = PriceInfo(amount = 155.08, currencyCode = "EUR"),
                retailPrice = PriceInfo(amount = 120.9, currencyCode = "EUR")
            ),
            accessInfo = AccessInfo(
                country = "FR",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED"
            )
        ),
        Book(
            id = "2",
            volumeInfo = VolumeInfo(
                title = "The Stories of Jazz",
                authors = listOf("Mario Dunkel"),
                publisher = "Hollitzer Wissenschaftsverlag",
                publishedDate = "2021-09-22",
                description = "New Orleans jazz, Dixieland, Chicago jazz, swing, bebop, cool jazz, hard bop, and free jazz: up until today, the history of jazz is told as a \"tradition\" consisting of fixed components including a succession of jazz styles. How did this construction of music history emerge? What were the alternative perspectives? And why did the narrative of a fixed tradition catch on? In this study, Mario Dunkel examines narratives of jazz history from the beginnings of jazz until the late 1950s. According to Dunkel, the jazz tradition is simultaneously an attempt to approach historical reality and the product of competition between different narratives and cultural myths. From the middlebrow culture of the 1920s to the New Deal, the African American civil rights movement and the role of the U.S. in the Cold War, Dunkel shows in detail how the jazz tradition, as a global narrative of the twentieth century, is intertwined with greater social and cultural developments.",
                pageCount = 406,
                printType = "BOOK",
                categories = listOf("Music"),
                maturityRating = "NOT_MATURE",
                language = "en",
                canonicalVolumeLink = "https://play.google.com/store/books/details?id=WNsp9dcmCtgC"
            ),
            saleInfo = SaleInfo(
                country = "FR",
                saleability = "FOR_SALE",
                isEbook = true,
                listPrice = PriceInfo(amount = 69.99, currencyCode = "EUR"),
                retailPrice = PriceInfo(amount = 69.99, currencyCode = "EUR")
            ),
            accessInfo = AccessInfo(
                country = "FR",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED",
            ),
        ),
        Book(
            id = "3",
            volumeInfo = VolumeInfo(
                title = "L'odyssée du jazz",
                authors = listOf("Noël Balen"),
                publishedDate = "2024-11-27",
                pageCount = 0,
                printType = "BOOK",
                maturityRating = "NOT_MATURE",
                language = "en",
                canonicalVolumeLink = "https://play.google.com/store/books/details?id=g4JDEAAAQBAJ"
            ),
            saleInfo = SaleInfo(
                country = "FR",
                saleability = "NOT_FOR_SALE",
                isEbook = false
            ),
            accessInfo = AccessInfo(
                country = "FR",
                viewability = "NO_PAGES",
                embeddable = false,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED",
            )
        ),
        Book(
            id = "4",
            volumeInfo = VolumeInfo(
                title = "Knowing Jazz",
                authors = listOf("Ken Prouty"),
                publisher = "Univ. Press of Mississippi",
                publishedDate = "2011-12-06",
                description = "Ken Prouty argues that knowledge of jazz, or more to the point, claims to knowledge of jazz, are the prime movers in forming jazz's identity, its canon, and its community. Every jazz artist, critic, or fan understands jazz differently, based on each individual's unique experiences and insights. Through playing, listening, reading, and talking about jazz, both as a form of musical expression and as a marker of identity, each aficionado develops a personalized relationship to the larger jazz world. Through the increasingly important role of media, listeners also engage in the formation of different communities that not only transcend traditional boundaries of geography, but increasingly exist only in the virtual world. The relationships of \"jazz people\" within and between these communities is at the center of Knowing Jazz. Some groups, such as those in academia, reflect a clash of sensibilities between historical traditions. Others, particularly online communities, represent new and exciting avenues for everyday fans, whose involvement in jazz has often been ignored. Other communities seek to define themselves as expressions of national or global sensibility, pointing to the ever-changing nature of jazz's identity as an American art form in an international setting. What all these communities share, however, is an intimate, visceral link to the music and the artists who make it, brought to life through the medium of recording. Informed by an interdisciplinary approach and approaching the topic from a number of perspectives, Knowing Jazz charts a philosophical course in which many disparate perspectives and varied opinions on jazz can find common ground.",
                pageCount = 219,
                printType = "BOOK",
                categories = listOf("Music"),
                maturityRating = "NOT_MATURE",
                language = "en",
                canonicalVolumeLink = "https://play.google.com/store/books/details?id=fSJK3fGUug8C"
            ),
            saleInfo = SaleInfo(
                country = "FR",
                saleability = "FOR_SALE",
                isEbook = true,
                listPrice = PriceInfo(amount = 44.31, currencyCode = "EUR"),
                retailPrice = PriceInfo(amount = 44.31, currencyCode = "EUR")
            ),
            accessInfo = AccessInfo(
                country = "FR",
                viewability = "PARTIAL",
                embeddable = true,
                publicDomain = false,
                textToSpeechPermission = "ALLOWED"
            ),
        )
    )

    val bookList : BooksList = BooksList(fakeDataBook)

    val fakeSuccessBookshelfUiStateAfterInit = BookshelfUiState.Success(
        userGoogleKeyWord = "",
        booksList = emptyList()
    )

    val fakeSuccessBookshelfUiStateAfterSearch = BookshelfUiState.Success(
        userGoogleKeyWord = "jazz",
        booksList = fakeDataBook
    )

    val fakeSuccessBookshelfUiStateAfterBookClick = BookshelfUiState.Success(
        userGoogleKeyWord = "jazz",
        booksList = fakeDataBook,
        currentBook = fakeDataBook.first(),
        isShowingDetailsBook = true
    )

    val fakeSuccessBookshelfUiStateAfterRetry = BookshelfUiState.Success(
        userGoogleKeyWord = "jazz",
        booksList = fakeDataBook
    )

    val fakeSuccessBookshelfUiStateAfterReset = BookshelfUiState.Success(
        isShowingDetailsBook = false,
        currentBook = null,
        userGoogleKeyWord = "jazz",
        booksList = fakeDataBook
    )

    val fakeSuccessBookshelfUiStateAfterUpdateUser = BookshelfUiState.Success(
        userGoogleKeyWord = "jazz",
        booksList = fakeDataBook
    )

    val fakeErrorBookshelfUiState = BookshelfUiState.Error(
        message = "Network error, please check your connection.",
        userGoogleKeyWord = "jazz"
    )

    val fakeLoadingBookshelfUiState = BookshelfUiState.Loading

}