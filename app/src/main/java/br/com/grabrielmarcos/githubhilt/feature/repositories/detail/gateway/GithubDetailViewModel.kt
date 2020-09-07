package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.gateway

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.grabrielmarcos.githubhilt.feature.base.gateway.BaseViewModel
import br.com.grabrielmarcos.githubhilt.feature.repositories.detail.business.RepositoryData
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import javax.inject.Inject

private const val STATE_OPEN = "open"
private const val STATE_CLOSE = "close"

class GithubDetailViewModel @Inject constructor(
    private val getRepositoryDetailUseCase: GetRepositoryDetailUseCase
) : BaseViewModel() {

    var isNetworkAvailable = true

    internal var detailOnLoading: MutableLiveData<Unit> = MutableLiveData()
    internal var detailOnSuccess: MutableLiveData<List<GithubDetailModel>> = MutableLiveData()
    internal var detailOnError: MutableLiveData<Unit> = MutableLiveData()
    internal var detailHeaderValue: MutableLiveData<Pair<Int, Int>> = MutableLiveData()

    fun getRepositoryDetail(param: RepositoryData?) {
        getRepositoryDetailUseCase.asFlow(viewModelScope, param) {
            onSuccess = {
                detailOnSuccess.value = it.data
                countDetailStatus(it.data)
            }
            onLoading = { detailOnLoading.value = Unit }
            onError = {
                detailOnError.value = Unit
            }
        }
    }

    private fun countDetailStatus(data: List<GithubDetailModel>) {
        detailHeaderValue.value = Pair(
            data.count { it.state == STATE_OPEN },
            data.count { it.state == STATE_CLOSE }
        )
    }

}
