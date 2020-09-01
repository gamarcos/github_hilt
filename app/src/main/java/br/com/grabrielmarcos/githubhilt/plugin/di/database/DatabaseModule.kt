package br.com.grabrielmarcos.githubhilt.plugin.di.database

import android.app.Application
import android.content.Context
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDAO
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): GithubRepositoryDatabase {
        return GithubRepositoryDatabase.getInstanceDatabase(application)
    }

    @Provides
    @Singleton
    fun providesGithubRepositoryDAO(database: GithubRepositoryDatabase): GithubRepositoryDAO {
        return database.getRepositoryDao()
    }
}