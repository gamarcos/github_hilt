package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.view

import android.os.Bundle
import android.os.Handler
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.grabrielmarcos.githubhilt.R
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.BaseViewModel
import br.com.grabrielmarcos.githubhilt.feature.base.view.BaseActivity
import br.com.grabrielmarcos.githubhilt.feature.base.view.Navigation
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway.GithubDetailViewModel
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.extetions.hide
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.extetions.show
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.view.RepositoryAdapter
import br.com.grabrielmarcos.githubhilt.feature.repositories.webview.view.GithubWebViewActivity
import kotlinx.android.synthetic.main.activity_detail.*

class GithubDetailActivity: BaseActivity() {

    private lateinit var viewmodel: GithubDetailViewModel
    private lateinit var adapter: GithubDetailAdapter

    override fun initViewModel(): BaseViewModel? =
        viewmodelFactory.create(GithubDetailViewModel::class.java).also { viewmodel = it }


    override fun onNetworkConnectionChangedStatus(isConnected: Boolean) {
        viewmodel.isNetworkAvailable = isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewmodel.getRepositoryDetail(getArgs())

        setupAdapter()
        setupObservable()
    }

    private fun getArgs() = intent.getSerializableExtra(REPOSITORY_ARGS) as RepositoryData?

    private fun setupObservable() {
        viewmodel.detailOnSuccess.observe(this, Observer { data ->
            adapter.setData(data)
            detail_progress.hide()
        })

        viewmodel.detailOnError.observe(this, Observer {
            detail_progress.hide()
            Handler().postDelayed( { finish() }, 1000)
        })

        viewmodel.detailOnLoading.observe(this, Observer { detail_progress.show() })

        viewmodel.detailHeaderValue.observe(this, Observer { headerValues ->
            opened_pr_count.text = getString(R.string.header_pullrequest_opened, headerValues.first.toString())
            closed_pr_count.text = getString(R.string.header_pullrequest_closed, headerValues.second.toString())
            detail_progress.hide()
        })
    }

    private fun setupAdapter() {
        adapter = GithubDetailAdapter {
            Navigation.navigate(this, GithubWebViewActivity::class.java,
                bundleOf(Pair(GithubWebViewActivity.WEBVIEW_ARGS, it.html)))
        }
        detail_pr_list.adapter = adapter
    }

    companion object {
        const val REPOSITORY_ARGS = "REPOSITORY_ARGS"
    }
}