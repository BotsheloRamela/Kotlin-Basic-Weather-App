# BasicWeatherApp

Welcome to **BasicWeatherApp**, a simple yet robust weather application built using Kotlin. This project 
is designed with scalability, testability, and maintainability in mind, making it an excellent 
demonstration of modern Android development practices.

## Overview

**BasicWeatherApp** fetches real-time weather data from the OpenWeatherMap API and presents it in a user-friendly 
interface. The app is built using the MVVM (Model-View-ViewModel) pattern combined with Clean Architecture 
principles, ensuring that the code is well-organized, testable, and easy to maintain.

## Architecture

### MVVM + Clean Architecture

The app is structured following the **MVVM (Model-View-ViewModel)** pattern combined with **Clean Architecture** 
principles. Here’s a breakdown of why this combination was chosen:

- **Separation of Concerns**: MVVM separates the UI (View) from the business logic (ViewModel), 
and Clean Architecture further decouples the domain layer from the data and presentation layers. 
This separation ensures that each part of the app is focused on a single responsibility, making 
it easier to understand, test, and maintain.

- **Testability**: By using MVVM and Clean Architecture, the app’s business logic is contained within 
the ViewModel and Use Cases, which can be unit tested independently of the UI. This separation allows 
for more comprehensive testing of the app’s functionality.

- **Scalability**: As the app grows in complexity, the architecture allows for the easy addition of new 
features. Clean Architecture’s layered approach means that changes in one layer (e.g., the data source) 
have minimal impact on other layers.

- **Maintainability**: The clear separation between layers makes it easier to refactor and update the 
app over time. Each layer can evolve independently, minimizing the risk of introducing bugs during updates.

### Layered Architecture

1. **Presentation Layer**:
    - **View**: Composed of Jetpack Compose components, this layer handles the UI. It observes the 
   ViewModel and reacts to changes in the UI state.
    - **ViewModel**: The ViewModel fetches data from the Use Cases, processes it, and exposes it to 
   the View as LiveData or State. It handles UI-related logic and ensures that the View only updates when necessary.

2. **Domain Layer**:
    - **Use Cases**: This layer contains the business logic of the app. Each Use Case encapsulates a 
   specific piece of functionality, such as fetching weather data. The Use Cases are orchestrated by the ViewModel.
    - **Repository Interface**: This interface abstracts the data sources, allowing the ViewModel to 
   be unaware of where the data comes from (network, local database, etc.).

3. **Data Layer**:
    - **Repository Implementation**: The Repository implements the Repository Interface and is responsible 
   for fetching data from remote (via Retrofit) or local sources.
    - **Data Sources**: Includes the APIs, database, and other sources of data. In this app, Retrofit 
   is used for network calls to the OpenWeatherMap API.

## Technologies Used

### 1. **Kotlin**
- **Reason**: Kotlin is the modern language for Android development, offering concise syntax, null 
safety, and full interoperability with Java.

### 2. **Jetpack Compose**
- **Reason**: Compose is the modern toolkit for building native UIs in Android. It simplifies UI 
development by using a declarative approach, making the code more readable and maintainable.

### 3. **Retrofit**
- **Reason**: Retrofit is a type-safe HTTP client for Android and Java, allowing easy and efficient 
API communication. It simplifies the process of interacting with the OpenWeatherMap API.

### 4. **Dagger Hilt**
- **Reason**: Dagger Hilt is used for dependency injection, reducing boilerplate code and simplifying 
the management of dependencies across the app. This makes the app more modular, testable, and scalable.

### 5. **Coroutines**
- **Reason**: Coroutines provide a way to write asynchronous code sequentially, making it easier to 
manage background tasks like network requests without blocking the main thread.

### 6. **LiveData/State**
- **Reason**: LiveData (or State in Compose) is used to observe data changes in the ViewModel and 
update the UI reactively. It ensures that the UI remains in sync with the data while handling the 
lifecycle of the components.

### 7. **OpenWeatherMap API**
- **Reason**: OpenWeatherMap provides reliable and comprehensive weather data that can be easily 
integrated with Retrofit.

## Setup and Installation

### Prerequisites
- Android Studio Bumblebee or later
- Kotlin 1.6 or later
- OpenWeatherMap API key

### Installation Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/BotsheloRamela/Kotlin-Basic-Weather-App
   cd BasicWeatherApp
   ```
2. Open the project in Android Studio:
    - Launch Android Studio.
    - Select "Open an existing project" and navigate to the cloned repository.

3. Add your OpenWeatherMap API key:
    - Create a file named local.properties in the root directory of the project.
    - Add the following line:
        ```properties
        WEATHER_API_KEY=your_api_key
        ```
4. Build and run the project:
    - Sync the Gradle files.
    - Build the project and run it on your emulator or connected device.

## Testing

### Unit Testing
- Unit tests cover the ViewModel and Use Cases to ensure business logic is correct.
- The Repository Interface is mocked to isolate the domain logic from the data sources.

### Run Tests
- Use the following command to run the tests:
    ```bash
    ./gradlew test
    ```
