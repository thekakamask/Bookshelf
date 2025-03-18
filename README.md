# 📚 **Bookshelf**
**Bookshelf** is a modern Android application that allows users to search and display a list of books with their images using the Google Books API. The project follows Android development best practices, implementing the MVVM architecture and leveraging tools such as Retrofit, Gson, and Coroutines for efficient network calls and data processing.

## ✅ **LAST MAJOR UPDATES**
   - Implemented temporary **detailsScreen** to display books details.
   - Implemented **ErrorScreen** in case of network error.
   - Implemented **LoadingScreen** during books loading.
   - Created extension functions to modify "HTTP" request into "HTTPS"  and to transform a list into a String.
   - Implemented **ViewModel** functions to update UI state.

## ❌ **NEXT UPDATES**
   - Improve **detailsScreen** to display better books details.
   - Improve **ViewModel** functions to update UI state.
   - Implement searching features to permit user to search a book among books displaying.

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
   
      - ✅ **Done** Implement lazy loading for efficient image handling.
      - ✅ **Done** Use Coil for optimized image fetching.
      - 🟩 **In progress** Optimize UI scrolling and animations.
      
   - 🛠 Error Handling & User Feedback:

      - 🟩 **In progress** Displays appropriate error messages for network failures.
      - ✅ **Done** Provides loading indicators for better UX.

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
   - Scroll through the grid of books to explore them.
3. **Click on a book into the grid**:
   - Click on a image of the books list.
   - Read the book details choosen.
4. **Return on the Home Screen**:
   - click on the TopBar BackArrow or the AndroidBackButton to come back to HomeScreen.
   - Choose other book.

## 📸 **Screenshots**
- **Home screen**:

   ![Home screen](screenshots/home_screen.png)

- **Error screen**:

   ![Error screen](screenshots/error_screen.png)

- **Details screen**:

   ![Details screen](screenshots/details_screen.png)


## 🤝 **Contributions**
Contributions are welcome! Feel free to fork the repository and submit a pull request for new features or bug fixes✅🟩❌.