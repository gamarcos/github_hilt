package br.com.grabrielmarcos.githubhilt.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubOwnerModel(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("login")
    val login: String? = "",
    @field:SerializedName("avatar_url")
    val avatar: String? = "",
    @field:SerializedName("url")
    val url: String? = "",
    @field:SerializedName("html_url")
    val personalPage: String? = "",
    @field:SerializedName("name")
    val name: String? = ""
) : Serializable