package br.com.grabrielmarcos.githubhilt.plugin.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}