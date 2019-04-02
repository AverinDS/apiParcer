package com.dmitry.apiparcer

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.RepositoryJson
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("repositories")
    fun getPublicRepositoriesSince(@Query("since") lastRepositoryId: Int): Observable<List<RepositoryJson>>

    @GET("{commitsUrl}")
    fun getCommits(@Path("commitsUrl") commitsUrl: String): Observable<List<CommitJson>>
}