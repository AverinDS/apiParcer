package com.dmitry.apiparcer.repositories

import android.util.Log
import com.dmitry.apiparcer.models.RepositoryModel
import io.reactivex.Observable
import io.reactivex.functions.Function4
import org.json.JSONObject

class InteractorImpl(
    private val networkRepository: NetworkRepository
) : Interactor {

    override fun getRepositoriesDataSinceId(latestRepositoryId: Int): Observable<List<Interactor.RepositoryData>> {
        return networkRepository
            .requestRepositoriesSinceId(latestRepositoryId)
            .map { it.take(5) }//TODO:DEBUG
            .map { listRepositoryJson ->
                listRepositoryJson.map { RepositoryModel(it) }
            }
            .map { repositoryModelList ->
                repositoryModelList.map { repositoryModel -> convertToRepositoryData(repositoryModel) }
            }
            .flatMap { requests ->
                Observable.zip(requests) { arrayRepositoryData ->
                    arrayRepositoryData.toList().map { it as Interactor.RepositoryData }
                }
            }
            .onErrorReturn { ex ->
                Log.e(InteractorImpl::class.java.simpleName, ex.message)
                emptyList()
            }
    }

    private fun convertToRepositoryData(repositoryModel: RepositoryModel): Observable<Interactor.RepositoryData> {
        val requestLanguages =
            networkRepository.requestLanguagesFromUrl(repositoryModel.languagesUrl).onErrorReturn { JSONObject() }
        val requestCommits =
            networkRepository.requestCommitsFromUrl(repositoryModel.commitsUrl).onErrorReturn { emptyList() }
        val requestForks = networkRepository.requestForksFromUrl(repositoryModel.forksUrl).onErrorReturn { emptyList() }
        val requestStars =
            networkRepository.requestStarGazers(repositoryModel.stargazersUrl).onErrorReturn { emptyList() }

        return Observable.zip(
            requestLanguages,
            requestCommits,
            requestForks,
            requestStars,
            Function4 { languagesJson, listCommits, listForks, listStarGazers ->
                RepositoryDataImpl(
                    name = repositoryModel.name,
                    description = repositoryModel.description,
                    forksCount = listForks.count(),
                    starCount = listStarGazers.count(),
                    owner = OwnerDataImpl(repositoryModel.owner.avatarUrl, repositoryModel.owner.login),
                    commits = listCommits.map {
                        CommitDataImpl(it.author.name, it.message, it.message)
                    },
                    languages = listOf(languagesJson.keys().toString())
                )
            })
    }

    data class RepositoryDataImpl(
        override val name: String,
        override val description: String,
        override val forksCount: Int,
        override val starCount: Int,
        override val owner: Interactor.OwnerData,
        override val commits: List<Interactor.CommitData>,
        override val languages: List<String>
    ) : Interactor.RepositoryData

    data class CommitDataImpl(
        override val author: String,
        override val commitMessage: String,
        override val commiter: String
    ) : Interactor.CommitData

    data class OwnerDataImpl(
        override val avatarUrl: String,
        override val login: String
    ) : Interactor.OwnerData
}