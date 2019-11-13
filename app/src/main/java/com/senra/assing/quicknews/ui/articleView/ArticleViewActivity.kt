package com.senra.assing.quicknews.ui.articleView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.senra.assing.quicknews.R
import com.senra.assing.quicknews.databinding.ActivityArticleViewBinding
import im.delight.android.webview.AdvancedWebView

class ArticleViewActivity : AppCompatActivity(), AdvancedWebView.Listener {

    private lateinit var binding : ActivityArticleViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_view)
        val url =  intent.getStringExtra("url")
        binding.webview.setListener(this, this)
        binding.webview.loadUrl(url)
    }
    override fun onPageFinished(url: String?) {
        binding.progressBar.visibility = View.GONE
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, resources.getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        binding.progressBar.visibility = View.VISIBLE
    }


}