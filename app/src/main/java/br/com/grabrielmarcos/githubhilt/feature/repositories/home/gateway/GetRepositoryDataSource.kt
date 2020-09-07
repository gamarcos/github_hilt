package br.com.grabrielmarcos.githubhilt.feature.repositories.home.gateway

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import br.com.grabrielmarcos.githubhilt.feature.base.business.PageParams
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import javax.inject.Inject

//TODO:: Create a generic structure to not repeat code
class GetRepositoryDataSource @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : PositionalDataSource<GithubRepositoryModel>() {

    private val page =
        PageParams(
            DEFAULT_PAGE_LIMIT,
            0
        )

    private lateinit var actionRetry: () -> Unit

    val state = MutableLiveData<DataSourceState>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<GithubRepositoryModel>) {

        getRepositoriesUseCase.asFlow(param = page) {
            onLoading = { state.postValue(DataSourceState.LOADING) }

            onSuccess = { result ->
                callback.onResult(result.data.items, page.offset, page.limit)
                state.postValue(DataSourceState.DONE)
            }

            onError = {
                setInitialLoadRetryAction(params, callback)
                state.postValue(DataSourceState.ERROR)
            }
        }
    }

    private fun setInitialLoadRetryAction(
        params: LoadInitialParams, callback:
        LoadInitialCallback<GithubRepositoryModel>
    ) {
        actionRetry = { loadInitial(params, callback) }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GithubRepositoryModel>) {
        page.offset = params.startPosition

        getRepositoriesUseCase.asFlow(param = page) {
            onLoading = { state.postValue(DataSourceState.LOADING) }

            onSuccess = { result ->
                callback.onResult(result.data.items)
                state.postValue(DataSourceState.DONE)
            }

            onError = {
                setRangeLoadRetryAction(params, callback)
                state.postValue(DataSourceState.ERROR)
            }
        }
    }

    private fun setRangeLoadRetryAction(
        params: LoadRangeParams,
        callback: LoadRangeCallback<GithubRepositoryModel>
    ) {
        actionRetry = { loadRange(params, callback) }
    }

    fun doRetry() {
        actionRetry.invoke()
    }

    companion object {
        private const val DEFAULT_PAGE_LIMIT = 20
    }
}