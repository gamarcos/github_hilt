package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import kotlinx.coroutines.flow.Flow

interface GithubDetailRepository {
    fun getGithubRepositoryDetail(name: String, repo: String): Flow<List<GithubDetailModel>>
}