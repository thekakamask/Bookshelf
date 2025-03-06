# 📚 **Bookshelf**
**Bookshelf** is a modern Android application that allows users to search and display a list of books with their images using the Google Books API. The project follows Android development best practices, implementing the MVVM architecture and leveraging tools such as Retrofit, Gson, and Coroutines for efficient network calls and data processing.

## ✅ **LAST MAJOR UPDATES**
   - Implemented **ViewModel** to manage UI state and interact with the Repository.
   - Implemented **UiState sealed interface** with data classes (`Success`, `Error`, `Loading`) to handle different API response states.
   - Integrated **LiveData** or **StateFlow** to observe data in the UI layer.
   - Created the **AppTopBar** with the application title display.

## ❌ **NEXT UPDATES**
   - Implement **ViewModel** functions to update UI state.
   - Implement **LazyGrid** to display books from **Google Books** and to permit user to scroll between books displaying.
   - Implement searching features to permit user to search a book among books displaying.

## 📋 **Features**
   - 📚 Display a list of books :

      - ❌ **Not implemented** Fetch and display a list of books from the Google Books API.
      - ❌ **Not implemented** Show book details including title, author, and image.

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
   
      - ❌ **Not implemented** Implement lazy loading for efficient image handling.
      - ❌ **Not implemented** Use Coil/Glide for optimized image fetching.
      - 🟩 **In progress** Optimize UI scrolling and animations.
      
   - 🛠 Error Handling & User Feedback:

      - 🟩 **In progress** Displays appropriate error messages for network failures.
      - ❌ **Not implemented** Provides loading indicators for better UX.

## 🛠️ **Tech Stack**
   - **Kotlin**: Modern, concise language for Android development.
   - **Jetpack Compose**: Declarative UI toolkit for Android.
   - **Material 3**: Modern, accessible user interface.
   - **StateFlow**: Reactive state management for real-time updates.
   - **ViewModel**: MVVM architecture to separate business logic from user interface.
   - **Retrofit**: Effectuer des appels réseau vers l'API REST.
   - **State Management**: Handle states with MutableStateOf.
   - **Coil**: Download, buffer, decode and cache images
   - **Gson**: Parse JSON responses.
   
## 🚀 **How to Use**
❌ This section is not implemented yet.

## 📸 **Screenshots**
❌ This section is not implemented yet.

## 🤝 **Contributions**
Contributions are welcome! Feel free to fork the repository and submit a pull request for new features or bug fixes✅🟩❌.