package com.walid44443.newsapp.data.local.dao


import androidx.paging.PagingSource
import androidx.room.*
import com.walid44443.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getAllNewsPaging(): PagingSource<Int, NewsEntity>

    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE uuid = :uuid")
    suspend fun getNewsById(uuid: String): NewsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntity)

    @Query("DELETE FROM news")
    suspend fun clearAllNews()

    @Query("SELECT COUNT(*) FROM news")
    suspend fun getNewsCount(): Int
}