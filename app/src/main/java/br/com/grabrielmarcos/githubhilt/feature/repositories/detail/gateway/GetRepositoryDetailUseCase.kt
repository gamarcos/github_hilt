package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import br.com.grabrielmarcos.githubhilt.feature.base.business.UseCase
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryDetailUseCase @Inject constructor(
    private val repository: GithubDetailRepository
): UseCase<RepositoryData, List<GithubDetailModel>>() {
    override fun execute(param: RepositoryData?): Flow<List<GithubDetailModel>> {
        return repository.getGithubRepositoryDetail(param?.login  ?: "", param?.repo ?: "")
    }
}