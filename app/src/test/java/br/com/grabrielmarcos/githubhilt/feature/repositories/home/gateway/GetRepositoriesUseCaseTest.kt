package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRepositoriesUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private var repository: GithubRepository = mock()

    private lateinit var usecase: GetRepositoriesUseCase

    @Before
    fun setUp() {
        usecase = GetRepositoriesUseCase(repository)
    }

    @Test
    fun `when get repository list then assert success`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        val params = PageParams(0,0)
        given { repository.getGithubRepositories(params) }.willReturn(flowOf(GithubRepositoriesModel()))

        usecase.execute(params)

        verify(repository, times(1)).getGithubRepositories(params)
    }

    @Test
    fun `when get repository throw exception then assert error`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        val error = RuntimeException()
        val params = PageParams(0,0)
        given { repository.getGithubRepositories(params) }.willThrow(error)

        verify(repository, times(0)).getGithubRepositories(params)
    }
}