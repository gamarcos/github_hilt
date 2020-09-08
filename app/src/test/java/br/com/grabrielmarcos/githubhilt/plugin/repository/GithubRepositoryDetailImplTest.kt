package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
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
class GithubRepositoryDetailImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private var api: API = mock()

    private lateinit var repository: GithubRepositoryDetailImpl

    @Before
    fun setUp() {
        repository = GithubRepositoryDetailImpl(api)
    }

    @Test
    fun `when request detail assert that call one time`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        given { repository.getGithubRepositoryDetail("", "") }.willReturn(
            flowOf(listOf(GithubDetailModel(id = 0, owner = GithubOwnerModel(0))))
        )

        repository.getGithubRepositoryDetail("", "")

        verify(api, times(1)).requestGithubDetail("", "")
    }
}