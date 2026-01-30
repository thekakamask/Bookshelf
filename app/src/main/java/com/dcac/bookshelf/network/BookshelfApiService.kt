package com.dcac.bookshelf.network


import com.dcac.bookshelf.model.Book
import com.dcac.bookshelf.model.BooksList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.dcac.bookshelf.BuildConfig


interface BookshelfApiService {

    // SEARCH BOOK BY PERSONALIZED REQUEST
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BooksList

    // OBTAIN SPECIFIC BOOK BY ID
    @GET("volumes/{volumeId}")
    suspend fun getBookById(
        @Path("volumeId") bookId: String, // Ex: "C1MI_4nZyD4C"
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): Book
}