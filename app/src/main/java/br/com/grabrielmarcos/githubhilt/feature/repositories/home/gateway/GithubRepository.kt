package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun deleteRepositories()
    fun getRepositoryByName(id: Long): Flow<GithubRepositoryModel>
    fun getGithubRepositories(pageParams: PageParams): Flow<GithubRepositoriesModel>
    fun getRepositoryOwner(ageParams: PageParams): Flow<GithubOwnerModel>
}