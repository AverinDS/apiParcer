package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.RepositoryJson
import io.reactivex.Observable

class NetworkRepositoryImpl(private val api: GitHubApi) : NetworkRepository {

    override fun requestCommitsFromUrl(urlCommits: String): Observable<List<CommitJson>> {
        return api.getCommits(urlCommits)
    }

    override fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJson>> {
        return api.getPublicRepositoriesSince(lastId)
    }
}