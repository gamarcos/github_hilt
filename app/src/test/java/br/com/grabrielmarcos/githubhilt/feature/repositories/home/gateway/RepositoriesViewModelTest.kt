package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoriesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository: GithubRepository = mock()

    private var api: API = mock()

    private lateinit var viewmodel: RepositoriesViewModel

    private lateinit var usecaseFactory: GetRepositoryDataSourceFactory

    private lateinit var datasource: GetRepositoryDataSource

    private lateinit var repositoryUseCase: GetRepositoriesUseCase

    private var actionRepositoriesObserver = mock<Observer<PagedList<GithubRepositoryModel>>>()

    @Before
    fun setUp() {
        setupResponse()

        repositoryUseCase = GetRepositoriesUseCase(repository)
        datasource = GetRepositoryDataSource(repositoryUseCase)
        usecaseFactory = GetRepositoryDataSourceFactory(datasource)
        viewmodel = RepositoriesViewModel(usecaseFactory).apply {
            actionRepositories.observeForever(actionRepositoriesObserver)
        }
    }

    private fun setupResponse() {
        val expectedValue = GithubRepositoriesModel(
            items = listOf(
                GithubRepositoryModel(0, owner = GithubOwnerModel(0)),
                GithubRepositoryModel(0, owner = GithubOwnerModel(0)),
                GithubRepositoryModel(0, owner = GithubOwnerModel(0))
            )
        )
        val params = PageParams(0, 0)
        given { repository.getGithubRepositories(params) }.willReturn(flowOf(expectedValue))
    }

    @Test
    fun `when list of repository return success assert value into viewmodel`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            verify(actionRepositoriesObserver).onChanged(viewmodel.actionRepositories.value)
        }
}