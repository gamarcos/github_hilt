package br.com.grabrielmarcos.githubhilt.feature.repositories.view

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.DataSourceState
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel

class RepositoryAdapter(
    private val onRetryClick: () -> Unit,
    private val onDetailClick: (GithubRepositoryModel) -> Unit
) : PagedListAdapter<GithubRepositoryModel, RecyclerView.ViewHolder>(repositoryDiffCallback) {

    private var state = DataSourceState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) RepositoryViewHolder.create(parent)
        else FeedbackViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as RepositoryViewHolder).bind(onDetailClick, getItem(position))
        else
            (holder as FeedbackViewHolder).bind(onRetryClick, state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == DataSourceState.LOADING || state == DataSourceState.ERROR)
    }

    fun setState(state: DataSourceState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    companion object {
        private const val DATA_VIEW_TYPE = 1
        private const val FOOTER_VIEW_TYPE = 2

        val repositoryDiffCallback = object : DiffUtil.ItemCallback<GithubRepositoryModel>() {
            override fun areItemsTheSame(
                oldItem: GithubRepositoryModel,
                newItem: GithubRepositoryModel
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: GithubRepositoryModel,
                newItem: GithubRepositoryModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}