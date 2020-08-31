package br.com.grabrielmarcos.githubhilt.feature.repositories.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.grabrielmarcos.githubhilt.R
import br.com.grabrielmarcos.githubhilt.feature.repositories.extetions.hide
import br.com.grabrielmarcos.githubhilt.feature.repositories.extetions.show
import br.com.grabrielmarcos.githubhilt.feature.repositories.gateway.DataSourceState
import kotlinx.android.synthetic.main.item_feedback.view.*

class FeedbackViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(onRetryClick: () -> Unit?, state: DataSourceState?) {
        itemView.item_error_text_view.setOnClickListener { onRetryClick() }

        when (state) {
            DataSourceState.LOADING -> showLoadingState()
            DataSourceState.ERROR -> showErrorState()
            DataSourceState.DONE -> showDoneState()
        }
    }

    private fun showErrorState() {
        itemView.apply {
            item_progress_bar.hide()
            item_error_text_view.show()
        }
    }

    private fun showLoadingState() {
        itemView.apply {
            item_progress_bar.show()
            item_error_text_view.hide()
        }
    }

    private fun showDoneState() {
        itemView.apply {
            item_progress_bar.hide()
            item_error_text_view.hide()
        }
    }

    companion object {
        fun create(parent: ViewGroup): FeedbackViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feedback, parent, false)
            return FeedbackViewHolder(view)
        }
    }
}