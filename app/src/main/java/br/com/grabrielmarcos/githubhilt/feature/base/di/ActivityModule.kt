package br.com.grabrielmarcos.githubhilt.feature.base.di

import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.di.GithubDetailModule
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.view.GithubDetailActivity
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.di.RepositoriesListModule
import br.com.grabrielmarcos.githubhilt.feature.repositories.home.view.GithubRepositoriesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [RepositoriesListModule::class])
    abstract fun bindGithubRepositoriesActivity(): GithubRepositoriesActivity

    @ContributesAndroidInjector(modules = [GithubDetailModule::class])
    abstract fun bindGithubDetailActivity(): GithubDetailActivity

}