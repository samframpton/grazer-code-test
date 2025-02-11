# Grazer Code Test Android App
The app consists of a login screen and a user list screen. The login screen collects the user's credentials and uses them to authenticate with the Grazer API, the returned auth token is persisted using Jetpack Datastore. The auth token is used to fetch a list of users which are displayed on the user list screen, this screen contains a logout button which deletes the token and returns to the login screen. The token will also be deleted if it has expired and the API returns a 401 response code. The user will remain logged in if the app is closed due to the persisted token.

The app follows an MVVM design pattern and uses Google Android architecture recommendations. The UI is built entirely with Jetpack Compose using a single activity, each screen has a view model, Jetpack Navigation is used to navigate between screens. Data is made available to the UI via repositories with a Retrofit service used to interact with the Grazer API. Hilt dependency injection is used to provide the repository, datastore and API service implementations. Remote images are loaded using Coil. Unit tests use JUnit and the Coroutines Test library, fake test doubles are used for data sources.

## Used libraries
- Jetpack Compose
- Jetpack Datastore
- Jetpack Navigation
- Material Design
- Dagger/Hilt
- Retrofit + GSON Converter
- Coil
- JUnit + Coroutines Test

## Potential areas for improvement
- When the auth token expires the user is logged out, this could be avoided by using a refresh token. Also the auth token header could be added to API requests using an interceptor rather than having to add it to every API function individually.
- The users fetched from the API could be persisted to reduce API requests and allow offline use, e.g. in a Room database.
- The error handling could be greatly improved, no error messages are displayed and currently only an HTTP 401 code is handled.
- The provided images are not displaying correctly possibly due to a CloudFlare verification issue.
- Increase test coverage and types of test, UI tests, integration tests, etc.
- UI improvements, loading indicator on login screen, meaningful error messages, data validation, etc.
