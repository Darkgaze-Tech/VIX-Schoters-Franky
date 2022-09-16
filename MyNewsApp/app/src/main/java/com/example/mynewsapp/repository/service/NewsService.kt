package com.example.mynewsapp.repository.service

import com.example.mynewsapp.model.NewsData
import com.example.mynewsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {


    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") countryCode: String?,
        @Query("page") pageNumber: Int,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsData>


    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") keyword: String? = "apple",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("language") language: String?,
        @Query("pageSize") pageSize: String = "50"
    ): Response<NewsData>

}

