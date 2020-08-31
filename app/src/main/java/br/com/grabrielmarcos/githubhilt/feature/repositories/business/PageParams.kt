package br.com.grabrielmarcos.githubhilt.feature.repositories.business

data class PageParams(var limit: Int, var offset: Int = 0, var query: String? = null)