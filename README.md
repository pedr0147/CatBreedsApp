# Cat Breeds App

An Android application built with Kotlin and Jetpack Compose that allows users to explore various cat breeds, view detailed information, and mark their favorite breeds.

## Features

-  **Breed List**: Displays a list of cat breeds fetched from The Cat API.
-  **Search**: Filter breeds by name using a search bar.
-  **Favorites**: Mark/unmark breeds as favorites and view them in a separate tab.
-  **Details View**: Tap on a breed to see its description, temperament, origin, and image.
-  **Single API Call**: Designed to load all breed data in one call and reuse it across screens.
-  **Local Persistence**: Favorites are stored locally using Room Database.

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Navigation Compose**
- **Room Database**
- **Retrofit**
- **Coil** (for image loading)
- **The Cat API** (data source)

## 🚀 Getting Started

Clone this repository:
   ```bash
   git clone https://github.com/pedr0147/CatBreedsApp.git
