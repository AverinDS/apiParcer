package com.dmitry.apiparcer.repositories

import io.reactivex.Observable

interface Interactor {
    fun getRepositoriesDataSinceId(latestRepositoryId: Int): Observable<List<RepositoryData>>

    interface RepositoryData {
        val name: String
        val description: String
        val forksCount: Int
        val starCount: Int
        val owner: OwnerData
        val commits: List<CommitData>
        val languages: List<String>
    }

    interface CommitData {
        val author: String
        val commitMessage: String
        val commiter: String
    }

    interface OwnerData {
        val avatarUrl: String
        val login: String
    }
}