package com.senra.assing.quicknews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senra.assing.quicknews.databinding.LayoutNewsCardBinding
import com.senra.assing.quicknews.model.TopHeadlines.*

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    var items: List<Article> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = LayoutNewsCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = items[position]
        holder.bindArticle(repo)
    }


    class ViewHolder(
        private val binding: LayoutNewsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArticleViewModel()

        init {
            binding.viewModel = viewModel
        }

        fun bindArticle(article: Article) {
            viewModel.article = article
            binding.executePendingBindings()
        }
    }
}