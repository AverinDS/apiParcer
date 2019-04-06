package com.dmitry.apiparcer

import com.dmitry.apiparcer.json.ExtendedCommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {
    @GET("repositories")
    fun getPublicRepositoriesSince(
        @Query("since") lastRepositoryId: Int,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<RepositoryJson>>

    @GET
    fun getCommits(
        @Url commitsUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<ExtendedCommitJson>>

    @GET
    fun getStarGazers(
        @Url starGazersUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<StarGazersJson>>

    @GET
    fun getForks(
        @Url forksUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<List<ForkJson>>

    @GET
    fun getProgramLanguages(
        @Url languagesUrl: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Observable<JsonObject>
}