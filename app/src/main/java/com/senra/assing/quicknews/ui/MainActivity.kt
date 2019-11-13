package com.senra.assing.quicknews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.senra.assing.quicknews.App
import com.senra.assing.quicknews.DispatcherProvider
import com.senra.assing.quicknews.R
import com.senra.assing.quicknews.data.NewsService
import com.senra.assing.quicknews.data.local.NewsDB
import com.senra.assing.quicknews.data.remote.NewsAPI
import com.senra.assing.quicknews.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:TopHeadlinesViewModel

    @Inject lateinit var newsService : NewsService
    private lateinit var binding: ActivityMainBinding
    private val newsListAdapter = NewsListAdapter()

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return TopHeadlinesViewModel(newsService,
                DispatcherProvider()
            ) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopHeadlinesViewModel::class.java)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        viewModel.articleList.observe(
            this,
            Observer {
                it?.let {
                    newsListAdapter::items.set(it)
                    newsListAdapter.items
                }
            }
        )
    }
    private fun setupRecyclerView() {
        val recyclerView = binding.repoList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsListAdapter
    }

}
