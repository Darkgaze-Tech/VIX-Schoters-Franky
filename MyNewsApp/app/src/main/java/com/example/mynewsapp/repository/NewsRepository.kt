package com.example.mynewsapp.repository

import com.example.mynewsapp.model.Article
import com.example.mynewsapp.model.NewsData
import com.example.mynewsapp.repository.database.NewsDataBase
import com.example.mynewsapp.repository.service.RetrofitInstance
import com.example.mynewsapp.util.Constants


class NewsRepository(val db: NewsDataBase) {

    // From Apis
    suspend fun getNews(countryCode: String?, pageNumber: Int, category: String?): NewsData? {
        var response =
            RetrofitInstance.api.getTopNews(
                countryCode, pageNumber, category,
                Constants.API_KEY
            )
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun searchNews(
        keyword: String?,
        language: String?,
        sortedBy: String,
        maxResults: String
    ): NewsData? {
        var response =
            RetrofitInstance.api.searchNews(
                keyword, sortedBy,
                Constants.API_KEY, language, maxResults
            )
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    // Database
    fun insertArticle(article: Article) {
        db.NewsDao().insert(article)
    }

    fun getSavedArticle(): MutableList<Article> {
        return db.NewsDao().getArticles()
    }

    fun deleteArticle(articleUrl: String) {
        db.NewsDao().deleteArticle(articleUrl)
    }
}