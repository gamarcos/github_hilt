package br.com.grabrielmarcos.githubhilt.feature.repositories.di

import androidx.lifecycle.ViewModel
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.GithubRepository
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.RepositoriesViewModel
import br.com.grabrielmarcos.githubhilt.plugin.di.viewmodel.ViewModelKey
import br.com.grabrielmarcos.githubhilt.plugin.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
abstract class RepositoriesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @Reusable
    abstract fun bindGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}