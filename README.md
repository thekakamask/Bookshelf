# 📚 **Bookshelf**
**Bookshelf** is a modern Android application that allows users to search and display a list of books with their images using the Google Books API. The project follows Android development best practices, implementing the MVVM architecture and leveraging tools such as Retrofit, Gson, and Coroutines for efficient network calls and data processing.

## ✅ **LAST MAJOR UPDATES**
   - Implemented **LazyGrid** to display books from **Google Books** and to permit user to scroll between books displaying.
   - Implemented Coil to display images of books from **Google Books**.
   - Implemented way to change "HTTP" url from **Google Books** images into "HTTPS" url.

## ❌ **NEXT UPDATES**
   - Implement **ViewModel** functions to update UI state.
   - Implement searching features to permit user to search a book among books displaying.
   - Implement **detailsScreen** to display books details.

## 📋 **Features**
   - 📚 Display a list of books :

      - ✅ **Done** Fetch and display a list of books from the Google Books API.
      - 🟩 **In progress** Show book details including title, author, and image.

   - 🎨 **Modern and Fluid Interface**:

      - TopBar:
         - ✅ **Done** Display application title.
         - ❌ **Not implemented** Allow searching for books.
         - 🟩 **In progress** Implement dynamic UI behaviors.

      - Light/Dark Mode:
         - ✅ **Done** Fully supports Material 3 with adaptive light and dark themes.

   - 🔄 **Real-time status management**:

      - 🟩 **In progress** Use a ViewModel to handle API responses and manage UI state.
      - ✅ **Done** Implement StateFlow for reactive updates.

   - 🚀 Performance and responsiveness:
   
      - 🟩 **In progress** Implement lazy loading for efficient image handling.
      - 🟩 **In progress** Use Coil for optimized image fetching.
      - 🟩 **In progress** Optimize UI scrolling and animations.
      
   - 🛠 Error Handling & User Feedback:

      - 🟩 **In progress** Displays appropriate error messages for network failures.
      - 🟩 **In progress** Provides loading indicators for better UX.

## 🛠️ **Tech Stack**
   - **Kotlin**: Modern, concise language for Android development.
   - **Jetpack Compose**: Declarative UI toolkit for Android.
   - **Material 3**: Modern, accessible user interface.
   - **StateFlow**: Reactive state management for real-time updates.
   - **ViewModel**: MVVM architecture to separate business logic from user interface.
   - **Retrofit**: Make network call to API REST.
   - **State Management**: Handle states with MutableStateOf.
   - **Coil**: Download, buffer, decode and cache images
   - **Gson**: Parse JSON responses.
   - **Google Books API** : Use this API for retrieve books from internet.
   
## 🚀 **How to Use**
1. **Launch the App**:
   - Download the code and launch the app on an Android device or emulator. (Bad performance because in Debug Build Variant)
2. **Navigate on the Google Books list**:
   - Scroll through the grid of Books to explore them.

## 📸 **Screenshots**
- **Home screen**:

   ![Home screen](screenshots/home_screen.png)


## 🤝 **Contributions**
Contributions are welcome! Feel free to fork the repository and submit a pull request for new features or bug fixes✅🟩❌.