package com.dcac.bookshelf.data

import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun getBooksList(queries : List<String>): List<Book>
    suspend fun getBook(bookId : String) : Book
}

class GoogleBooksRepository (
    private val bookshelfApiService : BookshelfApiService
) : BookshelfRepository {

    override suspend fun getBooksList(queries: List<String>): List<Book> {
        val queryString = queries.joinToString("+") // ✅ Transform ["jazz", "history"] into "jazz+history"
        return bookshelfApiService.searchBooks(queryString).items ?: emptyList()
    }

    override suspend fun getBook(bookId: String): Book {
        return bookshelfApiService.getBookById(bookId)
    }
}