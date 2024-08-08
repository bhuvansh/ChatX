# chatX

## Overview

**chatX** is a modern, real-time chat room application built using Kotlin and Jetpack Compose. The app leverages Firebase Firestore for backend services, providing seamless user authentication, multiple chat rooms, and instant messaging capabilities.

## Features

- **User Authentication**: Secure login and registration using Firebase Authentication.
- **Multiple Chat Rooms**: Users can join different chat rooms and engage in conversations with multiple participants.
- **Real-time Messaging**: Messages are updated in real-time, ensuring seamless communication between users.
- **Room Database Integration**: Local storage using Room Database to manage data efficiently.
- **MVVM Architecture**: Clean architecture ensuring separation of concerns, easy testing, and scalability.
- **Jetpack Compose**: Fully implemented using modern UI components provided by Jetpack Compose.
- **Custom Message Data Class**: Messages are encapsulated in a `Message` data class, featuring fields for sender information, message text, timestamp, and more.

## Screenshots

<div align="center">
  <img src="https://github.com/user-attachments/assets/c9b942fe-7c7d-4e2a-acba-59076a722a0c" alt="Signup page" width="200" style="margin-right: 20px;"/>
  <img src="https://github.com/user-attachments/assets/24b7af09-5634-4e93-a808-1689ef3c31ea" alt="Login page" width="200" style="margin-right: 20px;"/>
  <img src="https://github.com/user-attachments/assets/d96580f1-bba7-498b-834d-35867192134e" alt="Chatroom page" width="200" style="margin-right: 20px;"/>
  <img src="https://github.com/user-attachments/assets/0db43bb2-8492-41f6-ab8a-32b00c3fe978" alt="Chat page" width="200" style="margin-right: 20px;"/>
  <img src="https://github.com/user-attachments/assets/5caa51a6-998c-498a-a641-a64accb9546e" alt="Chat page 2" width="200"/>
</div>

## App Demo
[![Watch the video](https://img.youtube.com/vi/fuP__TAByoc/maxresdefault.jpg)](https://youtu.be/fuP__TAByoc)

### Prerequisites

- Android Studio Bumblebee or later
- Kotlin 1.5+
- Firebase Account

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/bhuvansh/ChatX
   ```

2. **Open in Android Studio:**

   Open the project in Android Studio.

3. **Configure Firebase:**

   - Create a new project in Firebase.
   - Add an Android app to your Firebase project.
   - Download the `google-services.json` file and place it in the `app` directory.
   - Enable Firebase Authentication and Firestore in the Firebase console.

4. **Build and Run:**

   Build and run the app on an emulator or physical device.

## Usage

- **Sign Up / Login**: Users can sign up or log in using their email and password.
- **Join Chat Rooms**: After logging in, users can join existing chat rooms
- **Create Chat Rooms**: After logging in, users can also create new chatrooms based on their interests and purpose.
- **Send Messages**: Type and send messages within any chat room. Messages appear instantly for all users in the room.

## Project Structure

```
chatX/
├── app/                 # Main app module
│   ├── src/             # Source files
│   ├── build.gradle     # Gradle build script for the app module
├── data/                # Data layer (repositories, data sources, etc.)
├── ui/                  # UI layer (composables, themes, etc.)
├── viewmodel/           # ViewModels for managing UI-related data
├── repository/          # Repository classes for handling data operations
├── model/               # Data models, including the Message data class
├── utils/               # Utility classes and helpers
├── build.gradle         # Project-level build script
└── README.md            # Project readme file
```

## Dependencies

- **Jetpack Compose**: For building the UI.
- **Firebase Firestore**: For backend database services.
- **Firebase Authentication**: For user authentication.
- **Room Database**: For local data storage.
- **Kotlin Coroutines**: For managing background tasks.

## Contributing

Feel free to fork this repository and submit pull requests. Contributions are welcome!

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries, reach out at `bhuvanshbehal_co21a3_43@dtu.ac.in`.

---

You can fill in or modify the sections as needed to better suit your project.
