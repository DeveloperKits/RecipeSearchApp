<img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/8edb164d-66d6-4e06-8db4-aca51c4382f5" width="900" height="300" />

# Culinary Quest
It's a Recipe Search App where utilize the "Forkify" API from https://forkify-api.herokuapp.com/ to fetch and display recipes. 

## Features

- SplashScreen: A brief introduction to the app with a visually pleasing start.
- Registration Screen: Allows new users to create an account using Firebase Authentication.
- Login Screen: Enables existing users to sign in to their accounts.
- HomeScreen: The main interface of the app where users can search recipe and show the details of recipe.
- ProfileScreen: Allows users to view and edit their profile information stored in Firebase Realtime Database.

## Libraries Used

- Navigation Component
- Retrofit
- Kotlin Coroutines
- Firebase
- LiveData
- DataBinding

## Architecture

This application implements the MVVM (Model-View-ViewModel) architecture pattern, ensuring a separation of concerns and easier maintenance. It uses the Navigation Component for managing UI navigation, Retrofit for network calls, Coroutines for asynchronous tasks, and Firebase for user authentication and data storage. LiveData is used for observing data changes, and DataBinding for binding UI components in layouts to data sources.

## Design Patterns

Various design patterns are employed throughout the application, such as Repository for data operations and Singleton for Retrofit instance management.

## Prerequisites

- Android Studio Arctic Fox | 2020.3.1 Patch 2 or higher
- Min SDK version 27 (Oreo) or higher

## Getting Started

1. **Clone the repository:**

```bash
   git clone https://github.com/DeveloperKits/RecipeSearchApp/
```


2. **Open the project in Android Studio:**
   
   Launch Android Studio and select 'Open an Existing Project', then navigate to the cloned repository.


4. **Configure Firebase:**
   
   - Go to the Firebase Console.
   - Add a new project or use an existing one.
   - Register your application with Firebase, download the google-services.json file, and place it in the app/ directory.
   - Build the project:
   - In Android Studio, select 'Build' from the top menu, then 'Rebuild Project'.
   - Wait for the build to finish.


4. **Run the application:**
   
   - Connect an Android device or use the Android emulator.
   - Press 'Run' (the green play button) in Android Studio.


## ScreenShot

   
   <img src="https://user-images.githubusercontent.com/69858580/104105648-c6b40700-52d9-11eb-8873-d50b36603784.jpg" width="300" height="300" hspace="30"/><img src="https://user-images.githubusercontent.com/69858580/104105622-9b311c80-52d9-11eb-9fa6-55e5baad5550.jpeg" width="300" height="600" hspace="30"/>

   <img src="https://user-images.githubusercontent.com/69858580/104105669-e51a0280-52d9-11eb-905c-612d8c4dce7c.jpeg" width="300" height="600" hspace="30"/><img src="https://user-images.githubusercontent.com/69858580/104105684-07ac1b80-52da-11eb-848a-a2578b6d3d68.jpeg" width="300" height="600" hspace="30"/>

   <img src="https://user-images.githubusercontent.com/69858580/104105669-e51a0280-52d9-11eb-905c-612d8c4dce7c.jpeg" width="300" height="600" hspace="30"/><img src="https://user-images.githubusercontent.com/69858580/104105684-07ac1b80-52da-11eb-848a-a2578b6d3d68.jpeg" width="300" height="600" hspace="30"/>
   

   <p align="left" >
      <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/83dbced7-75e4-48aa-8b1c-2f9b98d00c05" width="600" height="320" hspace="30"/>
   </p>   


   ## Thank You
   

  
