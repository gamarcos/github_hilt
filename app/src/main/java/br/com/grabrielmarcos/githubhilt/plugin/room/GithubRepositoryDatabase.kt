package br.com.grabrielmarcos.githubhilt.plugin.room

import android.app.Application
import android.content.Context
import androidx.room.*
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import br.com.grabrielmarcos.githubhilt.model.OwnerConverter

@Database(entities = [GithubRepositoryModel::class], version = 1, exportSchema = true)
@TypeConverters(OwnerConverter::class)
abstract class GithubRepositoryDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): GithubRepositoryDAO

    companion object {
        private const val DB_NAME = "github_repository.db"

        @Volatile
        private var INSTANCE: GithubRepositoryDatabase? = null

        @JvmStatic
        fun getInstanceDatabase(application: Application): GithubRepositoryDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(application.baseContext).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): GithubRepositoryDatabase {
            return Room.databaseBuilder(context, GithubRepositoryDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}