package com.dcac.bookshelf.fakeDataAndUtils

import com.dcac.bookshelf.data.BookshelfRepository
import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.network.BookshelfApiService
import kotlinx.coroutines.delay

class FakeRepository(
    private val fakeApiService: BookshelfApiService = FakeApiService()
) : BookshelfRepository {

    override suspend fun getBooksList(query: String): List<Book> {
        delay(100)
        //return FakeDataSource.bookList.items ?: emptyList()
        return fakeApiService.searchBooks(query).items ?: emptyList()
    }

    override suspend fun getBook(bookId: String): Book {
//        return FakeDataSource.bookList.items?.find { it.id == bookId }
//            ?: throw IllegalArgumentException("Book with ID $bookId not found")
        return fakeApiService.getBookById(bookId)}
}