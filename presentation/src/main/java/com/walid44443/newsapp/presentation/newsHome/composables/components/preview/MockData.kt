package com.walid44443.newsapp.presentation.newsHome.composables.components.preview

import com.walid44443.newsapp.domain.model.News

object MockData {

    val sampleNews = News(
        id = "1",
        title = "Breaking: Major Technology Breakthrough Announced",
        description = "Scientists have made a significant discovery that could revolutionize the way we think about renewable energy. This breakthrough promises to change the landscape of sustainable technology.",
        imageUrl = "https://picsum.photos/400/200?random=1",
        url = "https://example.com/article/1",
        publishedAt = "2024-01-15T10:30:00Z",
        source = "Tech News Daily",
        categories = listOf("Technology", "Science", "Energy"),
        language = "en",
        relevanceScore = 0.95
    )

    val sampleNewsWithoutImage = News(
        id = "2",
        title = "Economic Markets Show Positive Growth",
        description = "Stock markets around the world are showing signs of recovery as investors respond positively to recent policy changes.",
        imageUrl = null,
        url = "https://example.com/article/2",
        publishedAt = "2024-01-15T08:15:00Z",
        source = "Financial Times",
        categories = listOf("Business", "Economics"),
        language = "en",
        relevanceScore = 0.87
    )

    val sampleNewsLongTitle = News(
        id = "3",
        title = "This is a Very Long Title That Should Demonstrate How Text Overflow Works in Our News Item Cards When the Title Exceeds the Maximum Number of Lines",
        description = "This is also a longer description to test how our UI handles text that might exceed the maximum number of lines we want to display in the card layout.",
        imageUrl = "https://picsum.photos/400/200?random=2",
        url = "https://example.com/article/3",
        publishedAt = "2024-01-14T16:45:00Z",
        source = "Lorem Ipsum News Network",
        categories = listOf("General", "Technology", "World News"),
        language = "en",
        relevanceScore = 0.72
    )

    val sampleNewsMinimal = News(
        id = "4",
        title = "Short Title",
        description = null,
        imageUrl = null,
        url = "https://example.com/article/4",
        publishedAt = "2024-01-14T12:00:00Z",
        source = "Brief News",
        categories = listOf("General"),
        language = "en",
        relevanceScore = null
    )

    val sampleNewsDifferentLanguage = News(
        id = "5",
        title = "Nouvelles importantes sur la technologie",
        description = "Une découverte majeure qui pourrait changer notre façon de voir la technologie.",
        imageUrl = "https://picsum.photos/400/200?random=3",
        url = "https://example.com/article/5",
        publishedAt = "2024-01-13T14:20:00Z",
        source = "Le Tech Journal",
        categories = listOf("Technologie", "Science"),
        language = "fr",
        relevanceScore = 0.89
    )

    val sampleNewsSports = News(
        id = "6",
        title = "Championship Finals Set to Begin This Weekend",
        description = "Two powerhouse teams will face off in what promises to be the most exciting championship match of the decade.",
        imageUrl = "https://picsum.photos/400/200?random=4",
        url = "https://example.com/article/6",
        publishedAt = "2024-01-12T18:30:00Z",
        source = "Sports Central",
        categories = listOf("Sports", "Entertainment"),
        language = "en",
        relevanceScore = 0.91
    )

    val sampleNewsList = listOf(
        sampleNews,
        sampleNewsWithoutImage,
        sampleNewsLongTitle,
        sampleNewsMinimal,
        sampleNewsDifferentLanguage,
        sampleNewsSports
    )
}