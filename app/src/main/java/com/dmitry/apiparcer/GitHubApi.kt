package com.dmitry.apiparcer

import com.dmitry.apiparcer.json.RepositoryJsonModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("repositories")
    fun getPublicRepositoriesSince(@Query("since") lastRepositoryId: Int): Observable<List<RepositoryJsonModel>>
}