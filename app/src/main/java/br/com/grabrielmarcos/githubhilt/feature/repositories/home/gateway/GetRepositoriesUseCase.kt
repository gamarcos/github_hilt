package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import br.com.grabrielmarcos.githubhilt.feature.base.business.UseCase
import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
): UseCase<PageParams, GithubRepositoriesModel>() {
    override fun execute(param: PageParams?): Flow<GithubRepositoriesModel> {
        return repository.getGithubRepositories(param!!)
    }
}