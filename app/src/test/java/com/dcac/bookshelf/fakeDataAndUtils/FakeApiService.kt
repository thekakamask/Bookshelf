package com.dcac.bookshelf.fakeDataAndUtils

import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BooksList
import com.dcac.bookshelf.network.BookshelfApiService

class FakeApiService : BookshelfApiService {

    override suspend fun searchBooks(query: String, maxResults: Int): BooksList {
        return FakeDataSource.bookList;
    }

    override suspend fun getBookById(bookId: String): Book {
        return FakeDataSource.bookList.items?.find { it.id == bookId }!!
    }

}