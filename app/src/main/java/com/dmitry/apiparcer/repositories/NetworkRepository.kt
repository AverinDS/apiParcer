package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.json.ExtendedCommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
import com.google.gson.JsonObject
import io.reactivex.Observable

interface NetworkRepository {
    /**
     * Request repositories from GitHub beginning from last repositoryId
     *
     * @param lastId ID of the last Repository that you've seen.
     */
    fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJson>>

    /**
     * Request commits from url contains in [RepositoryJson] in field commits_url
     */
    fun requestCommitsFromUrl(urlCommits: String): Observable<List<ExtendedCommitJson>>

    /**
     * Request starGazers from url contains in [RepositoryJson] in stargazers_url
     */
    fun requestStarGazers(urlStarGazers: String): Observable<List<StarGazersJson>>

    /**
     * Request forks from url contains in [RepositoryJson] in forks_url
     */
    fun requestForksFromUrl(urlForks: String): Observable<List<ForkJson>>

    /**
     * request programming language from url
     */
    fun requestLanguagesFromUrl(urlLanguage: String): Observable<JsonObject>
}