package br.com.grabrielmarcos.githubhilt.plugin.repository

import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoriesModel
import br.com.grabrielmarcos.githubhilt.plugin.retrofit.API
import br.com.grabrielmarcos.githubhilt.plugin.room.GithubRepositoryDAO
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
class GithubRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule =
        MainCoroutineRule()

    private var api: API = mock()
    private var dao: GithubRepositoryDAO = mock()

    private lateinit var repository: GithubRepositoryImpl

    @Before
    fun setUp() {
        repository = GithubRepositoryImpl(api, dao)
    }

    @Test
    fun `when request repository assert that call one time`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        val params = PageParams(0,0)
        given { repository.getGithubRepositories(params) }.willReturn(flowOf(GithubRepositoriesModel()))

        repository.getGithubRepositories(params)

        verify(api, times(1)).requestGithubRepository(params.offset, params.limit)
    }
}