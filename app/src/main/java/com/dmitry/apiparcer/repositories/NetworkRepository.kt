package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ForkJson
import com.dmitry.apiparcer.json.RepositoryJson
import com.dmitry.apiparcer.json.StarGazersJson
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
    fun requestCommitsFromUrl(urlCommits: String): Observable<List<CommitJson>>

    /**
     * Request starGazers from url contains in [RepositoryJson] in stargazers_url
     */
    fun requestStarGazers(urlStarGazers: String): Observable<List<StarGazersJson>>

    /**
     * Request forks from url contains in [RepositoryJson] in forks_url
     */
    fun requestForks(urlForks: String): Observable<List<ForkJson>>
}