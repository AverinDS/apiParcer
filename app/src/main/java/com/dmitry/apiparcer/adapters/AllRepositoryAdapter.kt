package com.dmitry.apiparcer.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.repositories.Interactor
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_repository.view.*

class AllRepositoryAdapter(
    private val goToDetails: PublishSubject<Interactor.RepositoryData>,
    private val listRepositories: List<Interactor.RepositoryData>
) :
    RecyclerView.Adapter<AllRepositoryAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRepositoryAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))
    }

    override fun onBindViewHolder(holder: AllRepositoryAdapter.ViewHolder, position: Int) {
        holder.mainText.text = listRepositories[position].name
        holder.secondaryText.text = listRepositories[position].owner.login
        holder.forkCountText.text = context.getString(R.string.forks_template, listRepositories[position].forksCount)
        holder.starCountText.text = context.getString(R.string.star_template, listRepositories[position].starCount)
        holder.languageText.text =
            context.getString(R.string.languages_template, getLanguagesAsString(listRepositories[position].languages))
        holder.commitsText.text = getCommitsInfoString(listRepositories[position].commits)
        holder.descriptionText.text =
            context.getString(R.string.description_template, listRepositories[position].description)
        Picasso.get().load(listRepositories[position].owner.avatarUrl).into(holder.iconAvatar)
    }

    override fun getItemCount(): Int = listRepositories.size

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.bodyItem.setOnClickListener {
            holder.expandedView.let { expandableView ->
                if (expandableView.isExpanded) {
                    expandableView.collapse(true)
                } else {
                    expandableView.expand(true)
                }
            }
        }

        holder.bodyItem.setOnLongClickListener {
            goToDetails.onNext(listRepositories[holder.adapterPosition])
            true
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.bodyItem.setOnClickListener(null)
        holder.bodyItem.setOnLongClickListener(null)
    }

    fun addItems(newItems: List<Interactor.RepositoryData>) {
        listRepositories.plus(newItems)
    }

    fun getLastIndex(): Int {
        return listRepositories.lastIndex
    }

    private fun getLanguagesAsString(languages: List<String>): String {
        val stringBuilder = StringBuilder()
        for (i in 0..languages.lastIndex) {
            stringBuilder.append(languages[i])
            if (i != languages.lastIndex) {
                stringBuilder.append("\n")
            }
        }
        return stringBuilder.toString()
    }

    private fun getCommitsInfoString(commits: List<Interactor.CommitData>): String {
        val stringBuilder = StringBuilder()
        for (commit in commits) {
            stringBuilder
                .append(context.getString(R.string.author_template, commit.commiter))
                .append(
                    context.getString(
                        R.string.commits_template,
                        getCommitTitle(commit.commitMessage)
                    )
                )

        }
        return stringBuilder.toString()
    }

    private fun getCommitTitle(commitMessage: String): String {
        val indexOfNewLine = commitMessage.indexOf("\n")
        return if (indexOfNewLine == -1) {
            commitMessage
        } else {
            commitMessage.substring(0, indexOfNewLine)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainText = itemView.mainText
        val secondaryText = itemView.secondaryText
        val iconAvatar = itemView.iconAvatar
        val iconStar = itemView.iconStar
        val expandedView = itemView.expandedView
        val bodyItem = itemView
        val forkCountText = itemView.forksText
        val starCountText = itemView.starsText
        val commitsText = itemView.commitsText
        val descriptionText = itemView.descriptionText
        val languageText = itemView.languageText
    }
}