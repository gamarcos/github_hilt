package br.com.grabrielmarcos.githubhilt.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class OwnerConverter {
    @TypeConverter
    fun stringToOwner(data: String?): GithubOwnerModel {
        val gson = Gson()
        return data?.run {
            gson.fromJson(data, GithubOwnerModel::class.java)
        } ?: GithubOwnerModel(0)
    }

    @TypeConverter
    fun ownerToString(data: GithubOwnerModel?): String? {
        val gson = Gson()
        return gson.toJson(data)
    }
}

