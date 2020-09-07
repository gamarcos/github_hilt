package br.com.grabrielmarcos.githubhilt.feature.base.gateway

import androidx.paging.PagedList

object PageListConfig {
    val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(5 * 2)
        .setEnablePlaceholders(false)
        .build()
}