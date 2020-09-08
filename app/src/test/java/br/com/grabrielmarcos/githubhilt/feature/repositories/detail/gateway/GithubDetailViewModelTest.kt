package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.grabrielmarcos.githubhilt.core.MainCoroutineRule
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import br.com.grabrielmarcos.githubhilt.model.GithubOwnerModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GithubDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository: GithubDetailRepository = mock()

    private lateinit var viewmodel: GithubDetailViewModel

    private lateinit var getRepositoryDetailUseCase: GetRepositoryDetailUseCase

    private var detailHeaderValueObserver = mock<Observer<Pair<Int, Int>>>()

    private var detailOnSuccessObserver = mock<Observer<List<GithubDetailModel>>>()

    private var detailOnErrorObserver = mock<Observer<Unit>>()

    private var detailOnLoadingObserver = mock<Observer<Unit>>()

    @Before
    fun setUp() {
        getRepositoryDetailUseCase = GetRepositoryDetailUseCase(repository)
        viewmodel = GithubDetailViewModel(getRepositoryDetailUseCase).apply {
            detailHeaderValue.observeForever(detailHeaderValueObserver)
            detailOnSuccess.observeForever(detailOnSuccessObserver)
            detailOnError.observeForever(detailOnErrorObserver)
            detailOnLoading.observeForever(detailOnLoadingObserver)
        }
    }

    @Test
    fun `when details return success assert that values was updated at livedata`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val expectedResult = listOf(
                GithubDetailModel(id = 0, state = STATE_OPEN, owner = GithubOwnerModel(0)),
                GithubDetailModel(id = 0, state = STATE_OPEN, owner = GithubOwnerModel(0)),
                GithubDetailModel(id = 0, state = STATE_CLOSE, owner = GithubOwnerModel(0))
            )

            val emmiter = flow { emit(expectedResult) }
            given { repository.getGithubRepositoryDetail("", "") }.willReturn(emmiter)

            viewmodel.getRepositoryDetail(RepositoryData("", ""))

            verify(detailOnSuccessObserver).onChanged(expectedResult)
            verify(detailHeaderValueObserver).onChanged(Pair(expectedResult.count
            { it.state == STATE_OPEN }, expectedResult.count { it.state == STATE_CLOSE })
            )
        }

    @Test
    fun `when detail return loading state assert that loading was dispatcher`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewmodel.getRepositoryDetail(RepositoryData("", ""))
            verify(detailOnLoadingObserver).onChanged(Unit)
        }
}