package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class NetworkRepositoryImpl(private val api: GitHubApi) : NetworkRepository {
    override fun requestLanguagesFromUrl(urlLanguage: String): Observable<JSONObject> {
        return api.getProgramLanguages(urlLanguage)
    }

    override fun requestStarGazers(urlStarGazers: String): Observable<List<StarGazersJson>> {
        return api.getStarGazers(urlStarGazers)
            .subscribeOn(Schedulers.io())
    }

    override fun requestForksFromUrl(urlForks: String): Observable<List<ForkJson>> {
        return api.getForks(urlForks)
            .subscribeOn(Schedulers.io())
    }

    override fun requestCommitsFromUrl(urlCommits: String): Observable<List<CommitJson>> {
        return api.getCommits(urlCommits)
            .subscribeOn(Schedulers.io())
    }

    override fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJson>> {
        return api.getPublicRepositoriesSince(lastId)
            .subscribeOn(Schedulers.io())
    }
}