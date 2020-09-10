package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway.GithubDetailRepository
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryDetailImpl @Inject constructor(
    private var api: API
) : GithubDetailRepository {
    override fun getGithubRepositoryDetail(name: String, repo: String): Flow<List<GithubDetailModel>> {
        return api.requestGithubDetail(name, repo)
    }

    override fun getGithubOwner(login: String): Flow<GithubOwnerModel> {
        return api.requestGithubOwner(login)
    }
}