package br.com.grabrielmarcos.githubhilt.plugin.retrofit

import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("search/repositories?q=language:Kotlin&sort=stars")
    fun requestCharacters(
        @Query("page") page: Int,
        @Query("per_page") quantity: Int
    ): Flow<GithubRepositoriesModel>
}