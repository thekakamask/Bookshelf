package com.dcac.bookshelf

import com.dcac.bookshelf.data.GoogleBooksRepository
import com.dcac.bookshelf.fakeDataAndUtils.FakeApiService
import com.dcac.bookshelf.fakeDataAndUtils.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BookshelfRepositoryTest {

    @Test
    fun bookshelfRepository_getBookList_verifyBookList() =
        runTest {
            val repository = GoogleBooksRepository(
                bookshelfApiService = FakeApiService()
            )
            assertEquals(FakeDataSource.bookList.items, repository.getBooksList("jazz"))
        }

    @Test
    fun bookshelfRepository_getBook_verifyBook() =
        runTest{
            val repository = GoogleBooksRepository(
                bookshelfApiService = FakeApiService()
            )
            assertEquals(FakeDataSource.bookList.items?.get(0), repository.getBook("0"))
        }
}