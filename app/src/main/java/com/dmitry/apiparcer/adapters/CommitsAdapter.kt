package com.dmitry.apiparcer.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.fragments.details_repository_fragment.DetailsRepositoryDialogView
import kotlinx.android.synthetic.main.item_commit.view.*

class CommitsAdapter(val listCommits: List<DetailsRepositoryDialogView.AuthorCommit>) :
    RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    lateinit var contex: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsAdapter.ViewHolder {
        contex = parent.context
        return ViewHolder(LayoutInflater.from(contex).inflate(R.layout.item_commit, parent, false))
    }

    override fun getItemCount(): Int {
        return listCommits.size
    }

    override fun onBindViewHolder(holder: CommitsAdapter.ViewHolder, position: Int) {
        holder.author.text = listCommits[position].author
        holder.commit.text = listCommits[position].commit
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author = itemView.authorCommit
        val commit = itemView.commitMessage
    }

}