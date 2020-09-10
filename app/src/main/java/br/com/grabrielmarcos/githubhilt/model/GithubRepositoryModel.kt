package br.com.grabrielmarcos.githubhilt.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "repositories")
data class GithubRepositoriesModel(
    @field:SerializedName("items")
    var items: List<GithubRepositoryModel> = emptyList()
) : Serializable

@Entity(tableName = "repository")
data class GithubRepositoryModel(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String? = "",
    @field:SerializedName("full_name")
    val full_name: String? = "",
    @field:SerializedName("description")
    val description: String? = "",
    @TypeConverters(OwnerConverter::class)
    @ColumnInfo(name = "owner_repository")
    @field:SerializedName("owner")
    var owner: GithubOwnerModel?,
    @field:SerializedName("stargazers_count")
    val starts: Int? = 0,
    @field:SerializedName("forks_count")
    val forks: Int? = 0
) : Serializable

