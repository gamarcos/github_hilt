package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.di

import androidx.lifecycle.ViewModel
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway.GithubDetailRepository
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway.GithubDetailViewModel
import br.com.grabrielmarcos.githubhilt.plugin.di.viewmodel.ViewModelKey
import br.com.grabrielmarcos.githubhilt.plugin.repository.GithubRepositoryDetailImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
abstract class GithubDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubDetailViewModel::class)
    abstract fun bindGithubDetailViewModel(viewModel: GithubDetailViewModel): ViewModel

    @Binds
    @Reusable
    abstract fun bindGithubDetailRepository(repository: GithubRepositoryDetailImpl): GithubDetailRepository
}