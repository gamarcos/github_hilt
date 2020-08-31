package br.com.grabrielmarcos.githubhilt.feature.repositories.view

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.grabrielmarcos.githubhilt.R
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.BaseViewModel
import br.com.grabrielmarcos.githubhilt.feature.base.view.BaseActivity
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.DataSourceState
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.RepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_github_repository.*


class MainActivity : BaseActivity() {

    private lateinit var viewmodel: RepositoriesViewModel
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdapter()
        observeGetRepositories()
        observeRepositoryDataSourceState()
    }

    override fun initViewModel(): BaseViewModel {
        viewmodel = viewmodelFactory.create(RepositoriesViewModel::class.java)
        return viewmodel
    }

    override fun onNetworkConnectionChangedStatus(isConnected: Boolean) {
        viewmodel.isNetworkAvailable = isConnected
    }

    private fun setupAdapter() {
        adapter = RepositoryAdapter(
            onRetryClick = { viewmodel.retryCharactersLoad() },
            onDetailClick = { }
        )
        repository_recycler.adapter = adapter
    }

    private fun observeGetRepositories() {
        viewmodel.actionRepositories.observe(
            this, Observer { repos ->
                adapter.submitList(repos)
            })
    }

    private fun observeRepositoryDataSourceState() {
        viewmodel.actionRepositoryDataSourceState.observe(
            this, Observer {
                setViewState(it)
            })
    }

    private fun setViewState(state: DataSourceState) {
        adapter.setState(state)

        when (state) {
            DataSourceState.LOADING -> {
                showLoadingState()
            }
            DataSourceState.ERROR -> {
                showErrorState()
            }
            DataSourceState.DONE -> {
                showDoneState()
            }
        }
    }

    private fun showErrorState() {}

    private fun showLoadingState() {}

    private fun showDoneState() {}
}