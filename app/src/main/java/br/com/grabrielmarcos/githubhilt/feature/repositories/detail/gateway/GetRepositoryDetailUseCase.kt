package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import br.com.grabrielmarcos.githubhilt.feature.base.business.UseCase
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetRepositoryDetailUseCase @Inject constructor(
    private val repository: GithubDetailRepository
) : UseCase<RepositoryData, List<GithubDetailModel>>() {
    override fun execute(param: RepositoryData?): Flow<List<GithubDetailModel>> {
        return repository.getGithubRepositoryDetail(param?.login ?: "", param?.repo ?: "")
            .flatMapConcat(::getPullRequests)
    }

    private suspend fun getPullRequests(pullrequests: List<GithubDetailModel>): Flow<List<GithubDetailModel>> {
        val pullrequestsByService = mutableListOf<GithubOwnerModel>()
        return flow {
            pullrequests.forEach {
                repository.getGithubOwner(it.owner?.login ?: "")
                    .catch {
                        emit(pullrequests)
                        return@catch
                    }
                    .collect { owner -> pullrequestsByService.add(owner) }
            }
            mapperPullrequests(pullrequests, pullrequestsByService).collect { emit(it) }
        }
    }

    private suspend fun mapperPullrequests(
        pullrequests: List<GithubDetailModel>,
        users: List<GithubOwnerModel>
    ): Flow<List<GithubDetailModel>> {
        val pullrequestsUpdated = mutableListOf<GithubDetailModel>()
        return flow {
            users.forEach { owner ->
                val mathPullrequestUser = pullrequests.find { it.owner?.id == owner.id }
                mathPullrequestUser?.owner = owner
                pullrequestsUpdated.add(mathPullrequestUser!!)
            }
            emit(pullrequestsUpdated)
        }
    }
}