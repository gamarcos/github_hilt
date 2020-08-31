package br.com.grabrielmarcos.githubhilt.feature.repositories.gateway

import br.com.grabrielmarcos.githubhilt.feature.base.business.UseCase
import br.com.grabrielmarcos.githubhilt.feature.repositories.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
): UseCase<PageParams, GithubRepositoriesModel>() {
    override fun execute(param: PageParams?): Flow<GithubRepositoriesModel> {
        return repository.getGithubRepositories(param!!)
    }
}