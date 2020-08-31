package br.com.grabrielmarcos.githubhilt.plugin.di.application

import android.app.Application
import br.com.grabrielmarcos.githubhilt.feature.base.di.MainActivityModuleBuilder
import br.com.grabrielmarcos.githubhilt.plugin.di.module.SetupConfigBuilderModule
import br.com.grabrielmarcos.githubhilt.plugin.main.GithubRepositoriesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModuleBuilder::class,
        SetupConfigBuilderModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun injectApplication(application: GithubRepositoriesApplication)
}