package com.example.mynewsapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.model.Article
import com.example.mynewsapp.model.NewsData
import com.example.mynewsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    var mutableLiveData: MutableLiveData<NewsData> = MutableLiveData()
    var articleMutableLiveData: MutableLiveData<MutableList<Article>> = MutableLiveData()

    //From Apis
    fun getNews(
        countryCode: String?,
        pageNumber: Int,
        category: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableLiveData.postValue(newsRepository.getNews(countryCode, pageNumber, category))
        }
    }

    fun searchNews(keyword: String?, language: String?, sortedBy: String, maxResults: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableLiveData.postValue(
                newsRepository.searchNews(
                    keyword,
                    language,
                    sortedBy,
                    maxResults
                )
            )
        }
    }

    //Database
    fun insertArticle(context: Context, article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.insertArticle(article)
        }
    }

    fun getSavedArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleMutableLiveData.postValue(newsRepository.getSavedArticle())
        }
    }

    fun deleteArticle(articleUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteArticle(articleUrl)
        }
    }
}