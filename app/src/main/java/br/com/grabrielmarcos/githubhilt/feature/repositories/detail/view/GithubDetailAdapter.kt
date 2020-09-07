package br.com.grabrielmarcos.githubhilt.feature.repositories.detail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.grabrielmarcos.githubhilt.R
import br.com.grabrielmarcos.githubhilt.model.GithubDetailModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_detail.view.*

class GithubDetailViewHolder(view: View) : RecyclerView.ViewHolder(view)

class GithubDetailAdapter(
    private val onDetailClick: (GithubDetailModel) -> Unit
) : RecyclerView.Adapter<GithubDetailViewHolder>() {

    private var pullrequests: MutableList<GithubDetailModel> = mutableListOf()

    fun setData(data: List<GithubDetailModel>) {
        pullrequests.clear()
        pullrequests.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pullrequests.size

    override fun onBindViewHolder(holder: GithubDetailViewHolder, position: Int) {
        pullrequests[position].run {
            holder.itemView.item_detail_pr_name.text = title
            holder.itemView.item_detail_pr_description.text = body
            showAvatarImage(holder.itemView.item_detail_pr_avatar, owner?.avatar ?: "")
            holder.itemView.item_detail_pr_user_name.text = owner?.login
            holder.itemView.item_detail_pr_complete_user_name.text = owner?.name
            holder.itemView.item_detail_content.setOnClickListener { onDetailClick(this) }
        }

    }

    private fun showAvatarImage(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubDetailViewHolder =
        GithubDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        )
}