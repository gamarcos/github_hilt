package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway.GithubDetailRepository
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepositoryDetailImpl @Inject constructor(
    private var api: API
) : GithubDetailRepository {
    override fun getGithubRepositoryDetail(name: String, repo: String): Flow<List<GithubDetailModel>> =
        api.requestGithubDetail(name, repo)
}