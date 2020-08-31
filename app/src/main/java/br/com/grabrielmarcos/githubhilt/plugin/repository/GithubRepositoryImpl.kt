package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.feature.repositories.business.PageParams
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.GithubRepository
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private var api: API,
    private val dao: GithubRepositoryDAO
) : GithubRepository {
    override fun getGithubRepositories(pageParams: PageParams): Flow<GithubRepositoriesModel> {
        return api.requestCharacters(pageParams.offset, pageParams.limit)
    }

    override fun saveRepositories(repos: GithubRepositoriesModel) = dao.insertRepositories(repos)

    override fun getRepositoryByName(name: String): Flow<GithubRepositoryModel> = dao.getRepositoryByName(name)

    override fun deleteRepositories() = dao.deleteAllRepositories()
}