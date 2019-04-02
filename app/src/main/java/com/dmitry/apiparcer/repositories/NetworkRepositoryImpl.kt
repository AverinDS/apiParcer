package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import io.reactivex.Observable

class NetworkRepositoryImpl(private val api: GitHubApi) : NetworkRepository {

    override fun requestStarGazers(urlStarGazers: String): Observable<List<StarGazersJson>> {
        return api.getStarGazers(urlStarGazers)
    }

    override fun requestForks(urlForks: String): Observable<List<ForkJson>> {
        return api.getForks(urlForks)
    }

    override fun requestCommitsFromUrl(urlCommits: String): Observable<List<CommitJson>> {
        return api.getCommits(urlCommits)
    }

    override fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJson>> {
        return api.getPublicRepositoriesSince(lastId)
    }
}