package br.com.grabrielmarcos.githubhilt.plugin.di.module

import br.com.grabrielmarcos.githubhilt.plugin.di.database.DatabaseModule
import br.com.grabrielmarcos.githubhilt.plugin.di.network.NetworkModule
import br.com.grabrielmarcos.githubhilt.plugin.di.viewmodel.ViewModelFactoryModule
import dagger.Module

@Module(includes = [NetworkModule::class, ViewModelFactoryModule::class, DatabaseModule::class])
abstract class SetupConfigBuilderModule