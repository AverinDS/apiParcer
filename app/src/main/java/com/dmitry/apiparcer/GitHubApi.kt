package com.dmitry.apiparcer

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("repositories")
    fun getPublicRepositoriesSince(
        @Query("since") lastRepositoryId: Int,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<RepositoryJson>>

    @GET("{commitsUrl}")
    fun getCommits(
        @Path("commitsUrl") commitsUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<CommitJson>>

    @GET("{starGazersUrl}")
    fun getStarGazers(
        @Path("starGazersUrl") starGazersUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<StarGazersJson>>

    @GET("{forksUrl}")
    fun getForks(
        @Path("forksUrl") forksUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<ForkJson>>

    @GET("{programLanguages}")
    fun getProgramLanguages(
        @Path("programLanguages") languagesUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<JSONObject>
}