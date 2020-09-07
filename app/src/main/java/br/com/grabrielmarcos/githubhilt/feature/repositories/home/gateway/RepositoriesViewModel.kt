package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.BaseViewModel
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.PageListConfig
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val factory: GetRepositoryDataSourceFactory
) : BaseViewModel() {

    val actionRepositoryDataSourceState = factory.state
    val actionRepositories: LiveData<PagedList<GithubRepositoryModel>>

    var isNetworkAvailable = true

    init {
        actionRepositories = LivePagedListBuilder<Int, GithubRepositoryModel>(factory, PageListConfig.config).build()
    }

    fun retryCharactersLoad() = factory.retryCharactersLoad()
}