package br.com.grabrielmarcos.githubhilt.feature.repositories.gateway

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.BaseViewModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val factory: GetRepositoryDataSourceFactory
) : BaseViewModel() {

    val actionRepositoryDataSourceState = factory.state
    val actionRepositories: LiveData<PagedList<GithubRepositoryModel>>

    var isNetworkAvailable = true

    init {
        val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(5 * 2)
        .setEnablePlaceholders(false)
        .build()

        actionRepositories = LivePagedListBuilder<Int, GithubRepositoryModel>(factory, config).build()
    }

    fun retryCharactersLoad() = factory.retryCharactersLoad()

}