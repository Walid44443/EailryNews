# 🎯 Domain Layer

The domain layer contains the core business logic of the application, implementing Clean
Architecture principles. This layer is independent of external frameworks and defines the business
rules and use cases.

## 🏗️ Architecture Components

### Domain Models

Pure Kotlin data classes representing business entities

## 📦 Core Components

### Resource Wrapper
Handles different states of data operations:

```kotlin
sealed class Resource<T> {
data class Success<T>(val data: T) : Resource<T>()
data class Error<T>(val message: String, val exception: Throwable? = null) : Resource<T>()
data class Loading<T>(val data: T? = null) : Resource<T>()
}
```

### Domain Models
Pure Kotlin data classes representing business entities:

```kotlin
data class Article(
val id: String,
val title: String,
val description: String?,
val content: String?,
val url: String,
val urlToImage: String?,
val publishedAt: String,
val source: Source
)

data class Source(
val id: String?,
val name: String
)
```

### Repository Interfaces
Abstract contracts for data access:

```kotlin
interface NewsRepository {
suspend fun getTopHeadlines(
country: String = "us",
page: Int = 1,
pageSize: Int = 20
): Flow<Resource<List<Article>>>

    suspend fun searchNews(
        query: String,
        page: Int = 1,
        pageSize: Int = 20
    ): Flow<Resource<List<Article>>>
}
```

### Use Cases
Business logic encapsulation following Single Responsibility Principle:

```kotlin
class GetNewsUseCase(
private val repository: NewsRepository
) {
suspend operator fun invoke(
country: String = "us",
page: Int = 1,
pageSize: Int = 20
): Flow<Resource<List<Article>>> {
return repository.getTopHeadlines(country, page, pageSize)
}
}
```


## 🎯 Use Cases

### News Use Cases
- **GetNewsUseCase**: Fetch latest news articles with pagination and country filtering
- **SearchNewsUseCase**: Search articles by query with pagination

### Implementation Example
```kotlin
class GetNewsUseCase(
private val repository: NewsRepository
) {
suspend operator fun invoke(
country: String = "us",
page: Int = 1,
pageSize: Int = 20
): Flow<Resource<List<Article>>> {
return repository.getTopHeadlines(country, page, pageSize)
}
}

class SearchNewsUseCase(
private val repository: NewsRepository
) {
suspend operator fun invoke(
query: String,
page: Int = 1,
pageSize: Int = 20
): Flow<Resource<List<Article>>> {
return repository.searchNews(query, page, pageSize)
}
}
```

## 📋 Best Practices

### Clean Architecture Principles
- **Independence**: No dependencies on external frameworks
- **Testability**: All components are easily testable
- **Single Responsibility**: Each class has one reason to change
- **Dependency Inversion**: Depend on abstractions, not concretions

### Use Case Guidelines
- **Single Purpose**: Each use case handles one business operation
- **Input Validation**: Validate all inputs before processing
- **Error Handling**: Return appropriate error types using Resource wrapper
- **Immutability**: Use immutable data structures

### Repository Pattern
- **Interface Segregation**: Define clear contracts for data access
- **Data Mapping**: Convert external data to domain models
- **Error Propagation**: Handle and propagate errors appropriately
- **Flow Usage**: Use Flow for reactive data streams

### Domain Model Guidelines
- **Pure Data Classes**: Keep models simple with no business logic
- **Nullable Properties**: Use nullable types for optional article fields
- **Immutable State**: Prefer immutable data structures like your Article and Source models
- **Clear Naming**: Use descriptive property names like urlToImage and publishedAt