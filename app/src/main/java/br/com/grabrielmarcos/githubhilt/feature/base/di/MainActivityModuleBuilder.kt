package br.com.grabrielmarcos.githubhilt.feature.base.di

import br.com.grabrielmarcos.githubhilt.feature.repositories.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModuleBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}