package br.com.grabrielmarcos.githubhilt.plugin.main

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.ConnectivityReceiver
import br.com.grabrielmarcos.githubhilt.plugin.di.application.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GithubRepositoriesApplication : Application(), HasAndroidInjector {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        registerConnectivityReceiver()
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initDependencyInjection() {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .injectApplication(this)
    }

    private fun registerConnectivityReceiver() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }
}