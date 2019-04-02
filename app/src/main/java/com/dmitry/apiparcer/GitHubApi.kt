package com.dmitry.apiparcer

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("repositories")
    fun getPublicRepositoriesSince(@Query("since") lastRepositoryId: Int): Observable<List<RepositoryJson>>

    @GET("{commitsUrl}")
    fun getCommits(@Path("commitsUrl") commitsUrl: String): Observable<List<CommitJson>>

    @GET("{starGazersUrl}")
    fun getStarGazers(@Path("starGazersUrl") starGazersUrl: String): Observable<List<StarGazersJson>>

    @GET("{forksUrl}")
    fun getForks(@Path("forksUrl") forksUrl: String): Observable<List<ForkJson>>
}