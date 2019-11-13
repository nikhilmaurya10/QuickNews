package com.senra.assing.quicknews.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senra.assing.quicknews.databinding.LayoutNewsCardBinding
import com.senra.assing.quicknews.model.TopHeadlines.*

class NewsListAdapter(private val articleClickListner: ((String) -> Unit)) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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
        return ViewHolder(binding, articleClickListner)
    }



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = items[position]
        holder.bindArticle(repo)
    }

    fun onArticleClicked(context : Context, url : String) {

    }


    class ViewHolder(
        private val binding: LayoutNewsCardBinding,
        private val articleClickListner: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArticleViewModel()

        init {
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                viewModel.url.let(articleClickListner::invoke)
            }
        }

        fun bindArticle(article: Article) {
            viewModel.article = article
            binding.executePendingBindings()
        }
    }

    interface onClickListner {
        fun onViewArticle(url : String)
    }
}