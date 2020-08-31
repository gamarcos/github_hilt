package br.com.grabrielmarcos.githubhilt.feature.repositories.gateway

import androidx.paging.DataSource
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import javax.inject.Inject

class GetRepositoryDataSourceFactory @Inject constructor(
    private val source: GetRepositoryDataSource
) : DataSource.Factory<Int, GithubRepositoryModel>() {

    val state = source.state

    override fun create(): DataSource<Int, GithubRepositoryModel> = source

    fun retryCharactersLoad() = source.doRetry()
}