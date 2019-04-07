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
    private var listRepositories: List<Interactor.RepositoryData>,
    private val loadNextRepositories: PublishSubject<Int>
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
        holder.commitsText.text =
            context.getString(R.string.commits_count_template, listRepositories[position].commits.size)
        holder.descriptionText.text =
            context.getString(R.string.description_template, listRepositories[position].description)
        listRepositories[position].owner.avatarUrl.let { url ->
            if (url.isNotEmpty()) {
                Picasso.get().load(url).into(holder.iconAvatar)
            }
        }
        holder.progressbar.visibility = if (position == listRepositories.lastIndex) {
            View.VISIBLE
        } else {
            View.GONE
        }
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
        if (holder.adapterPosition == listRepositories.lastIndex) {
            loadNextRepositories.onNext(listRepositories.last().id)
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.bodyItem.setOnClickListener(null)
        holder.bodyItem.setOnLongClickListener(null)
    }

    fun addItems(newItems: List<Interactor.RepositoryData>) {
        listRepositories += newItems
    }

    fun getLastIndex(): Int {
        return if (listRepositories.lastIndex == -1) {
            0
        } else {
            listRepositories.lastIndex
        }
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
        val progressbar = itemView.progressBarItem
    }
}