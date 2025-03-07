package com.dcac.bookshelf.data

import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun getBooksList(query : String): List<Book>
    suspend fun getBook(bookId : String) : Book
}

class GoogleBooksRepository (
    private val bookshelfApiService : BookshelfApiService
) : BookshelfRepository {

    override suspend fun getBooksList(query : String): List<Book> {
        return bookshelfApiService.searchBooks(query).items ?: emptyList()
    }

    override suspend fun getBook(bookId: String): Book {
        return bookshelfApiService.getBookById(bookId)
    }
}