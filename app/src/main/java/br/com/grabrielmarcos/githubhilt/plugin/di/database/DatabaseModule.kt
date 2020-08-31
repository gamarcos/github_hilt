package br.com.grabrielmarcos.githubhilt.plugin.di.database

import android.content.Context
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDAO
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): GithubRepositoryDatabase {
        return GithubRepositoryDatabase.getInstanceDatabase(context)
    }

    @Provides
    @Singleton
    fun providesCharacterDao(database: GithubRepositoryDatabase): GithubRepositoryDAO {
        return database.getRepositoryDao()
    }
}