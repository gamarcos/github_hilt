package br.com.grabrielmarcos.githubhilt.feature.repositories.webview.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.grabrielmarcos.githubhilt.R
import kotlinx.android.synthetic.main.activity_webview.*

class GithubWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webview_container.apply {
            settings.javaScriptEnabled = true
            settings.setAppCacheEnabled(true)
            setOnLongClickListener { true }
            loadUrl(getArgs())
        }
    }

    private fun getArgs() = intent.extras?.getString(WEBVIEW_ARGS) ?: DEFAULT_URL

    companion object {
        const val WEBVIEW_ARGS = "WEBVIEW_ARGS"
        private const val DEFAULT_URL = "https://github.com"
    }
}