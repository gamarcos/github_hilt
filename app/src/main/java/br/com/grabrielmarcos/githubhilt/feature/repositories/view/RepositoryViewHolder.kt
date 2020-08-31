package br.com.grabrielmarcos.githubhilt.feature.repositories.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.grabrielmarcos.githubhilt.R
import br.com.grabrielmarcos.githubhilt.model.GithubRepositoryModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        onDetailClick: (GithubRepositoryModel) -> Unit?,
        repositoryModel: GithubRepositoryModel?
    ) {
        repositoryModel?.run {
            itemView.item_repository_container.setOnClickListener { onDetailClick(repositoryModel) }

            itemView.item_repository_name.text = name
            itemView.item_repository_description.text = description
            //TODO :: rever infos
            itemView.item_repository_owner_name.text = name
            itemView.item_repository_owner_user.text = owner?.login
            itemView.item_repository_forks_count.text = forks?.toString()
            itemView.item_repository_stars_count.text = starts?.toString()
            showAvatar()
        }
    }

    private fun GithubRepositoryModel.showAvatar() {
        Glide.with(itemView)
            .load(owner?.avatar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.item_repository_avatar)
    }

    companion object {
        fun create(parent: ViewGroup): RepositoryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
            return RepositoryViewHolder(view)
        }
    }
}