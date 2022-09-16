package com.example.mynewsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.mynewsapp.databinding.FragmentDisplayNewsBinding
import com.example.mynewsapp.model.Article

class DisplayNewsFragment : Fragment() {

    lateinit var binding: FragmentDisplayNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayNewsBinding.inflate(inflater, container, false)
        displayNews(arguments)
        return binding.root
    }

    private fun displayNews(arguments: Bundle?) {
        if (arguments != null) {
            var news: Article = arguments.getSerializable("article") as Article

            binding.webView.apply {
                webViewClient = WebViewClient()
                news.url?.let { loadUrl(it) }
            }

        }

    }


}