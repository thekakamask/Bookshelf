import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    id("org.jetbrains.kotlin.kapt")
}


val localProperties = Properties().apply {
    load(FileInputStream(File(rootDir, "local.properties")))
}
val booksApiKey = localProperties.getProperty("BOOKS_API_KEY")
    ?: throw GradleException("BOOKS_API_KEY not found in local.properties")

android {
    namespace = "com.dcac.bookshelf"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dcac.bookshelf"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders["BOOKS_API_KEY"] = booksApiKey

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BOOKS_API_KEY", "\"$booksApiKey\"")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Enables Jetpack Navigation to be used with Jetpack Compose to manage navigation between screens.
    implementation(libs.androidx.navigation.compose)
    //Facilitates integration of ViewModel into Jetpack Compose to manage states and lifecycle.
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Provides additional Material Design icons for use in the user interface.
    implementation(libs.androidx.material.icons.extended)
    // Retrofit
    implementation(libs.retrofit)
    //Adds support for Kotlinx serialization to convert JSON responses with Retrofit.
    implementation(libs.retrofit.kotlinx.serialization)
    //Provides a powerful and efficient HTTP library to handle network requests.
    implementation(libs.okhttp)
    // Provides native serialization and deserialization of Kotlin objects to JSON.
    implementation(libs.kotlinx.serialization.json)
    //Coil Core, used to load images in classic XML Views and for cache management.
    implementation(libs.coil)
    // Integrates the Coil library into Jetpack Compose for optimized image loading and display.
    //implementation(libs.coil.compose)
    implementation(libs.coilCompose)
    //Allows ConstraintLayout to be used with Jetpack Compose for flexible, high-performance layouts.
    implementation(libs.constraintlayout.compose)
    //Makes it easy to integrate Google Fonts directly into Jetpack Compose.
    implementation(libs.androidx.ui.text.google.fonts)
    //Provides tools for managing local file and data storage in Android.
    implementation(libs.androidx.storage)
    //Adds smooth, declarative animations to enhance the user experience with Jetpack Compose.
    implementation(libs.compose.animation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.junit4.android)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Allows the application's main module to be included in Android instrumentation tests.
    androidTestImplementation(project(":app"))
    //Provides tools for testing navigation in a Jetpack Compose or classic Android application.
    androidTestImplementation(libs.androidx.navigation.testing)
    //Tests intentions (Intents) and interactions between activities with Espresso.
    androidTestImplementation(libs.androidx.espresso.intents)
    //Adds utilities for testing coroutine behavior and managing threading in unit tests.
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.turbine)


    //This library lets you create dummy objects (mocks), simulate their behavior and check interactions
    // in pure Java/Kotlin tests (outside Android). It only works in tests running on the JVM, not on an Android device.
    testImplementation(libs.mockito.core)
    // Allows you to use Mockito with inline mocking, notably for final objects, final methods, Kotlin objects, etc.
    //By default, Mockito cannot mock certain Kotlin elements (such as val, object, etc.), but this lib enables
    // advanced Kotlin bytecode support via the “inline” agent. Very useful in classic JVM testing, but useless in instrumentation testing.
    testImplementation(libs.mockito.inline)

    // Hilt's main library for injecting dependencies into your code (Activity, ViewModel, etc.).
    implementation(libs.hilt.android)

    // Annotation processor to automatically generate Hilt's injection code (required for @Inject, @HiltViewModel, etc.).
    kapt(libs.hilt.compiler)

    // Special version of Hilt for instrumented tests: allows you to inject fakes or mocks into Android tests (in androidTest/).
    androidTestImplementation(libs.hilt.android.testing)

}