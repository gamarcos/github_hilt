package br.com.grabrielmarcos.githubhilt.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "detail")
data class GithubDetailModel(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Long = 0,
    @field:SerializedName("title")
    val title: String? = "",
    @field:SerializedName("body")
    val body: String? = "",
    @field:SerializedName("state")
    val state: String? = "",
    @field:SerializedName("html_url")
    val html: String? = "",
    @TypeConverters(OwnerConverter::class)
    @ColumnInfo(name = "owner_detail")
    @field:SerializedName("user")
    val owner: GithubOwnerModel?
) : Serializable