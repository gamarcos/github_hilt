package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
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
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetRepositoryDetailUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private var repository: GithubDetailRepository = mock()

    private lateinit var usecase: GetRepositoryDetailUseCase

    @Before
    fun setUp() {
        usecase = GetRepositoryDetailUseCase(repository)
    }

    @Test
    fun `when get repository detail then assert return  success`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            given { repository.getGithubRepositoryDetail("", "") }.willReturn(
                flowOf(listOf(GithubDetailModel(0, owner = GithubOwnerModel(0))))
            )

            usecase.execute(RepositoryData("", ""))

            verify(repository, times(1)).getGithubRepositoryDetail("", "")
        }

    @Test
    fun `when get repository detail throw exception assert return error`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val error = RuntimeException()
            given { repository.getGithubRepositoryDetail("", "") }.willThrow(error)

            verify(repository, Mockito.times(0)).getGithubRepositoryDetail("", "")
        }
}
