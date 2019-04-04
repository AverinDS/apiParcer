package com.dmitry.apiparcer.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.repositories.Interactor
import kotlinx.android.synthetic.main.item_repository.view.*

class AllRepositoryAdapter(private val listRepositories: List<Interactor.RepositoryData>) :
    RecyclerView.Adapter<AllRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRepositoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))
    }

    override fun getItemCount(): Int = listRepositories.size

    override fun onBindViewHolder(holder: AllRepositoryAdapter.ViewHolder, position: Int) {
        holder.mainText.text = listRepositories[position].name
        holder.secondaryText.text = listRepositories[position].owner.login
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainText = itemView.mainText
        val secondaryText = itemView.secondaryText
        val iconAvatar = itemView.iconAvatar
        val iconStar = itemView.iconStar
    }
}