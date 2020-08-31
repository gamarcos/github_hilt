package br.com.grabrielmarcos.githubhilt.plugin.room

import androidx.room.*
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepositoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repositories: GithubRepositoriesModel)

    @Delete
    fun deleteAllRepositories()

    @Query("SELECT * FROM repository")
    fun selectAllRepositories(): Flow<GithubRepositoriesModel>

    @Query("SELECT * FROM repository WHERE name = :name")
    fun getRepositoryByName(name: String): Flow<GithubRepositoryModel>
}