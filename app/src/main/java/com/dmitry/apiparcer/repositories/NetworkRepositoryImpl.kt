package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.BuildConfig
import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.json.ExtendedCommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NetworkRepositoryImpl(private val api: GitHubApi) : NetworkRepository {
    override fun requestLanguagesFromUrl(urlLanguage: String): Observable<JsonObject> {
        return api.getProgramLanguages(urlLanguage, BuildConfig.CLIENT_KEY, BuildConfig.CLIENT_SECRET_KEY)
    }

    override fun requestStarGazers(urlStarGazers: String): Observable<List<StarGazersJson>> {
        return api.getStarGazers(urlStarGazers, BuildConfig.CLIENT_KEY, BuildConfig.CLIENT_SECRET_KEY)
            .subscribeOn(Schedulers.io())
    }

    override fun requestForksFromUrl(urlForks: String): Observable<List<ForkJson>> {
        return api.getForks(urlForks, BuildConfig.CLIENT_KEY, BuildConfig.CLIENT_SECRET_KEY)
            .subscribeOn(Schedulers.io())
    }

    override fun requestCommitsFromUrl(urlCommits: String): Observable<List<ExtendedCommitJson>> {
        return api.getCommits(urlCommits, BuildConfig.CLIENT_KEY, BuildConfig.CLIENT_SECRET_KEY)
            .subscribeOn(Schedulers.io())
    }

    override fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJson>> {
        return api.getPublicRepositoriesSince(lastId, BuildConfig.CLIENT_KEY, BuildConfig.CLIENT_SECRET_KEY)
            .subscribeOn(Schedulers.io())
    }
}