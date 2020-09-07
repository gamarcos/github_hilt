package br.com.grabrielmarcos.githubhilt.feature.base.business

data class PageParams(var limit: Int, var offset: Int = 0, var query: String? = null)