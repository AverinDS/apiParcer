package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.json.RepositoryJsonModel
import io.reactivex.Observable

interface NetworkRepository {
    /**
     * Request repositories from GitHub beginning from last repositoryId
     *
     * @param lastId ID of the last Repository that you've seen.
     */
    fun requestRepositoriesSinceId(lastId: Int): Observable<List<RepositoryJsonModel>>
}