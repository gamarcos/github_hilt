package br.com.grabrielmarcos.githubhilt.feature.base.di

import br.com.grabrielmarcos.githubhilt.feature.repositories.di.RepositoriesListModule
import br.com.grabrielmarcos.githubhilt.feature.repositories.view.MainActivity
import br.com.grabrielmarcos.githubhilt.plugin.di.qualifier.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [RepositoriesListModule::class])
    abstract fun bindMainActivity(): MainActivity
}