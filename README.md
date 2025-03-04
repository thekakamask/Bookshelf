# 📚 **Bookshelf**
**Bookshelf** is a modern Android application that allows users to search and display a list of books with their images using the Google Books API. The project follows Android development best practices, implementing the MVVM architecture and leveraging tools such as Retrofit, Gson, and Coroutines for efficient network calls and data processing.

## ✅ **LAST MAJOR UPDATES**
   - Implemented **data models**.
   - Integrated **Google Books API** with **Retrofit**. 
   - Configured **Retrofit** with **Kotlinx Serialization** for JSON parsing.
   - Implemented the **Repository Pattern** 
   - Set up **Dependency Injection** via an AppContainer for better modularity.

## ❌ **NEXT UPDATES**
   - Implement **ViewModel** to manage UI state and interact with the Repository.
   - Implement **UiState sealed interface** with data classes (`Success`, `Error`, `Loading`) to handle different API response states.
   - Integrate **LiveData** or **StateFlow** to observe data in the UI layer.

## 📋 **Features**
   - 📚 Display a list of books :

      - ❌ **Not implemented** Fetch and display a list of books from the Google Books API.
      - ❌ **Not implemented** Show book details including title, author, and image.

   - 🎨 **Modern and Fluid Interface**:

      - TopBar:
         - ❌ **Not implemented** Display application title.
         - ❌ **Not implemented** Allow searching for books.
         - ❌ **Not implemented** Implement dynamic UI behaviors.

      - Light/Dark Mode:
         - ✅ **Done** Fully supports Material 3 with adaptive light and dark themes.

   - 🔄 **Real-time status management**:

      - ❌ **Not implemented** Use a ViewModel to handle API responses and manage UI state.
      - ❌ **Not implemented** Implement StateFlow for reactive updates.

   - 🚀 Performance and responsiveness:
   
      - ❌ **Not implemented** Implement lazy loading for efficient image handling.
      - ❌ **Not implemented** Use Coil/Glide for optimized image fetching.
      - ❌ **Not implemented** Optimize UI scrolling and animations.
      
   - 🛠 Error Handling & User Feedback:

      - ❌ **Not implemented** Displays appropriate error messages for network failures.
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