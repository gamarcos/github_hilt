package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway.GithubRepository
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GithubRepositoryImpl @Inject constructor(
    private var api: API,
    private var dao: GithubRepositoryDAO
) : GithubRepository {

    override fun getGithubRepositories(pageParams: PageParams): Flow<GithubRepositoriesModel> {
        return api.requestGithubRepository(pageParams.offset, pageParams.limit)
//            .flatMapConcat { saveRepositories(it) }
    }

   private suspend fun saveRepositories(repos: GithubRepositoriesModel): Flow<GithubRepositoriesModel> {
       withContext(Dispatchers.IO) {
           launch {
               dao.insertRepositories(repos.items)
           }
       }
       return flow {
           dao.selectAllRepositories().collect { emit(GithubRepositoriesModel(it)) }
       }
    }

    override fun getRepositoryOwner(pageParams: PageParams): Flow<GithubOwnerModel> {
        return api.requestGithubOwner(pageParams.query ?: "")
    }

    override fun getRepositoryByName(id: Long): Flow<GithubRepositoryModel> = dao.getRepositoryById(id)

    override fun deleteRepositories() = dao.deleteAllRepositories()
}