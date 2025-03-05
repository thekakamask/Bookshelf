package com.dcac.bookshelf

import android.app.Application
import com.dcac.bookshelf.data.AppContainer
import com.dcac.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {

    private lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}