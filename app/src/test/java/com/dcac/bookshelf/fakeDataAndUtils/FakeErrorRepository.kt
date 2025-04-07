package com.dcac.bookshelf.fakeDataAndUtils

import com.dcac.bookshelf.data.BookshelfRepository
import com.dcac.bookshelf.model.Book
import java.io.IOException

class FakeErrorRepository(
    private var shouldFail: Boolean = false
) : BookshelfRepository {

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    override suspend fun getBooksList(keyword: String): List<Book> {
        kotlinx.coroutines.yield() // emit "Loading"
        if (shouldFail) throw IOException("Simulated network error")
        return FakeDataSource.bookList.items!!
    }

    override suspend fun getBook(bookId: String): Book {
        TODO("Not yet implemented")
    }
}