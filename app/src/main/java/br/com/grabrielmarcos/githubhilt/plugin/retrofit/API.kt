package br.com.grabrielmarcos.githubhilt.plugin.retrofit

import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("search/repositories?q=language:Kotlin&sort=stars")
    fun requestGithubRepository(
        @Query("page") page: Int,
        @Query("per_page") quantity: Int
    ): Flow<GithubRepositoriesModel>

    @GET("user/{user_name}")
    fun requestGithubOwner(
        @Path("user_name") userName: String
    ): Flow<GithubOwnerModel>

    @GET("/repos/{name}/{repo}/pulls")
    fun requestGithubDetail(
        @Path("name") name: String,
        @Path("repo") repo: String
    ): Flow<List<GithubDetailModel>>
}