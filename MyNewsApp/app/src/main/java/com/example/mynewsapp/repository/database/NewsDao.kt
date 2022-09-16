package com.example.mynewsapp.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynewsapp.model.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getArticles(): MutableList<Article>

    @Query("DELETE FROM articles WHERE url = :articleUrl")
    fun deleteArticle(articleUrl: String)
}