# 📱 Presentation Layer

The presentation layer contains all UI-related components built with Jetpack Compose, following the MVI (Model-View-Intent) pattern for a reactive and predictable user interface.

## 🏗️ Architecture Pattern

### MVI (Model-View-Intent)
- **Model**: Represents the UI state (data classes)
- **View**: Composable functions that observe state
- **Intent**: User actions that trigger state changes

### State Management
Each screen follows a consistent pattern:
```kotlin
// State representation
data class NewsHomeState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)

// ViewModel handling business logic
class NewsHomeViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    // State and actions
}
```


## 📱 Screens

### Home Screen (`NewsHomeScreen.kt`)
- **Purpose**: Display list of news articles with real-time updates
- **Features**:
    - Pull-to-refresh functionality
    - Infinite scrolling with pagination
    - Search and filtering capabilities
    - Article preview with images and metadata
- **State**: `NewsHomeState` - manages articles list, loading, and error states
- **Navigation**: Navigate to article details on item click

### Detail Screen (`NewsDetailsScreen.kt`)
- **Purpose**: Show full article content and details
- **Features**:
    - Full article content display
    - Shared element transitions from home screen
    - Bookmark/favorite toggle functionality
    - Share article via system share sheet
    - WebView integration for external links
- **State**: `NewsDetailsState` - manages article content, bookmark status, and loading
- **Navigation**: Back to home, share external content
