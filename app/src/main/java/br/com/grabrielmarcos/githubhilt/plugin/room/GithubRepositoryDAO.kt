package br.com.grabrielmarcos.githubhilt.plugin.room

import androidx.room.*
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepositoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repositories: GithubRepositoryModel)

    @Query("DELETE FROM repository")
    fun deleteAllRepositories()

    @Query("SELECT * FROM repository")
    fun selectAllRepositories(): Flow<List<GithubRepositoryModel>>

    @Query("SELECT * FROM repository WHERE id = :id")
    fun getRepositoryById(id: Long): Flow<GithubRepositoryModel>
}