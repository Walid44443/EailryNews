# 📊 Data Layer

The data layer handles data operations, API communication, and remote data management for the news application.

## 🏗️ Architecture

### Data Layer Components
- **API Services**: Network communication with News API
- **Remote Data Sources**: Handle API requests and responses
- **Data Models**: Network response representations
- **DTOs**: Data Transfer Objects for API communication

## 📦 Core Components

### Remote Data Source
Interface and implementation for API communication:

```kotlin
interface RemoteDataSource {
suspend fun getNews(
page: Int = 1,
limit: Int = 20,
search: String? = null,
categories: String? = null
): NewsResponseDto
}

class RemoteDataSourceImpl(
private val apiService: NewsApiService,
private val apiKey: String
) : RemoteDataSource {

    override suspend fun getNews(
        page: Int,
        limit: Int,
        search: String?,
        categories: String?
    ): NewsResponseDto {
        return apiService.getNews(
            apiToken = apiKey,
            page = page,
            limit = limit,
            search = search,
            categories = categories
        )
    }
}
```

### Data Models
Network response models with proper serialization:

```kotlin
data class NewsResponseDto(
@SerializedName("data")
val articles: List<ArticleDto>,
@SerializedName("meta")
val meta: MetaDto
)

data class ArticleDto(
@SerializedName("uuid")
val uuid: String,
@SerializedName("title")
val title: String,
@SerializedName("description")
val description: String?,
@SerializedName("keywords")
val keywords: String?,
@SerializedName("snippet")
val snippet: String?,
@SerializedName("url")
val url: String,
@SerializedName("image_url")
val imageUrl: String?,
@SerializedName("language")
val language: String,
@SerializedName("published_at")
val publishedAt: String,
@SerializedName("source")
val source: String,
@SerializedName("categories")
val categories: List<String>,
@SerializedName("relevance_score")
val relevanceScore: Double?
)

data class MetaDto(
@SerializedName("found")
val found: Int,
@SerializedName("returned")
val returned: Int,
@SerializedName("limit")
val limit: Int,
@SerializedName("page")
val page: Int
)
```

## 🎯 Use Cases

### Data Management
- **Network Operations**: Handle API requests with pagination support
- **Search Functionality**: Search articles by query with filtering
- **Category Filtering**: Filter news by specific categories
- **Response Parsing**: Parse JSON responses to data models

### API Integration
- **News API Service**: Fetch news articles with metadata
- **Pagination Support**: Handle page-based data loading
- **Search Operations**: Search articles with flexible parameters
- **Category Management**: Support for multiple category filtering

## 📋 Best Practices

### Remote Data Source Pattern
- **Interface Segregation**: Clear contract for remote operations
- **Parameter Flexibility**: Optional parameters with default values
- **Error Handling**: Proper exception propagation from API layer
- **API Key Management**: Secure handling of authentication tokens

### Data Modeling
- **DTO Separation**: Keep data models separate from domain models
- **Gson Serialization**: Use proper JSON serialization annotations
- **Nullable Fields**: Handle optional API fields appropriately
- **Type Safety**: Use appropriate data types for API fields

### Network Layer
- **Pagination**: Implement proper page and limit parameters
- **Search Optimization**: Flexible search with optional parameters
- **Category Support**: Handle multiple categories efficiently
- **Response Metadata**: Include pagination and result metadata

### Performance
- **Parameter Optimization**: Use reasonable default values for pagination
- **Memory Management**: Handle large result sets efficiently
- **Network Efficiency**: Minimize unnecessary API calls
- **Data Size**: Optimize payload sizes with proper limit values