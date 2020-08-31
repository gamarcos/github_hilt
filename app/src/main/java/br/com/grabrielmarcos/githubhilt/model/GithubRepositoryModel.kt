package br.com.grabrielmarcos.githubhilt.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "repositories")
data class GithubRepositoriesModel(
    @field:SerializedName("items")
    val items: List<GithubRepositoryModel> = emptyList()
)

@Entity(tableName = "repository")
data class GithubRepositoryModel(
    @PrimaryKey
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("description")
    val description: String? = "",
    @Embedded
    @field:SerializedName("owner")
    val owner: Owner?,
    @field:SerializedName("stargazers_count")
    val starts: Int? = 0,
    @field:SerializedName("forks_count")
    val forks: Int? = 0
) : Serializable

@Entity(tableName = "owner")
data class Owner(
    @field:SerializedName("login")
    val login: String? = "",
    @field:SerializedName("avatar_url")
    val avatar: String? = "",
    @field:SerializedName("url")
    val url: String? = ""
) : Serializable