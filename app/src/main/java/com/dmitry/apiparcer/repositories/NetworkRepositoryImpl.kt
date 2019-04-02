package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.json.RepositoryJsonModel
import io.reactivex.Observable

class NetworkRepositoryImpl(private val api: GitHubApi) : NetworkRepository {

    override fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJsonModel>> {
        return api.getPublicRepositoriesSince(lastId)
    }
}