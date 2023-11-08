<img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/2516b218-551b-4f91-b349-69acdacfcf8a"/>

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

   
   <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/07798749-4704-4a32-8bc9-f48d6b03a564" width="300" height="600" hspace="10"/> <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/1afd7f78-02bc-4e06-966e-60023a2ebcac" width="300" height="600" hspace="30"/> 
   
   
   <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/f14f0f6a-c7d6-43fc-8fd4-555f71720a4f" width="300" height="600" hspace="10"/><img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/30064c1e-4bff-434e-a1fc-847fdde3347e" width="300" height="600" hspace="30"/> 
   
   <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/c8ae3d0e-77a0-4a5b-b38d-ebd81c6ca345" width="300" height="600" hspace="10"/> <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/b7af1eed-59ff-4215-82c9-d8675e76a0b2" width="300" height="600" hspace="30"/>
   
   
## Navigation graph   

   <p align="left" >
      <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/83dbced7-75e4-48aa-8b1c-2f9b98d00c05" width="600" height="320" hspace="10"/>
   </p>   


## Demo

<p align="left" >
      <img src="https://github.com/DeveloperKits/RecipeSearchApp/assets/69858580/5d7c14bf-62bb-4db4-a6a2-a02c992c50fb" width="300" height="600" hspace="10"/>
   </p>   

<br>

## Thank You
   

  
