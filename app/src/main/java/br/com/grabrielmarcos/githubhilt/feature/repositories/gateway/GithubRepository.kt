package br.com.grabrielmarcos.githubhilt.feature.repositories.gateway

import br.com.grabrielmarcos.githubhilt.feature.repositories.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun deleteRepositories()
    fun saveRepositories(repos: GithubRepositoriesModel)
    fun getRepositoryByName(name: String): Flow<GithubRepositoryModel>
    fun getGithubRepositories(pageParams: PageParams): Flow<GithubRepositoriesModel>
}