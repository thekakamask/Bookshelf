package com.dcac.bookshelf

import android.app.Application
import com.dcac.bookshelf.data.AppContainer
import com.dcac.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()

        /*val customImageLoader = ImageLoader.Builder(this)
            .logger(DebugLogger()) // Active les logs de Coil
            .crossfade(true) // Ajoute un fondu lors du chargement
            .build()

        // Stocker l'instance globale dans un objet accessible
        ImageLoaderSingleton.imageLoader = customImageLoader*/
    }
}

// Singleton pour stocker l'ImageLoader
/*
object ImageLoaderSingleton {
    lateinit var imageLoader: ImageLoader
}*/
