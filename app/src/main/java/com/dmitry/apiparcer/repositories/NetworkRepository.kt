package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.RepositoryJson
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
}