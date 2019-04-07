package com.dmitry.apiparcer.repositories

import com.dmitry.apiparcer.models.RepositoryModel
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.functions.Function4

const val REPOSITORIES_SIZE = 15

class InteractorImpl(
    private val networkRepository: NetworkRepository
) : Interactor {

    override fun getRepositoriesDataSinceId(latestRepositoryId: Int): Observable<List<Interactor.RepositoryData>> {
        return networkRepository
            .requestRepositoriesSinceId(latestRepositoryId)
            //api does not support request counts of commit, so it is constraint for better "Time to show" for UI
            .map { it.take(REPOSITORIES_SIZE) }
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
    }

    private fun convertToRepositoryData(repositoryModel: RepositoryModel): Observable<Interactor.RepositoryData> {
        val requestLanguages = networkRepository.requestLanguagesFromUrl(repositoryModel.languagesUrl)
            .onErrorReturn { JsonObject() }

        val requestCommits = networkRepository.requestCommitsFromUrl(repositoryModel.commitsUrl)
            .onErrorReturn { emptyList() }

        val requestForks = networkRepository.requestForksFromUrl(repositoryModel.forksUrl)
            .onErrorReturn { emptyList() }

        val requestStars = networkRepository.requestStarGazers(repositoryModel.stargazersUrl)
            .onErrorReturn { emptyList() }

        return Observable.zip(
            requestLanguages,
            requestCommits,
            requestForks,
            requestStars,
            Function4 { languagesJson, listCommits, listForks, listStarGazers ->
                RepositoryDataImpl(
                    id = repositoryModel.id,
                    name = repositoryModel.name,
                    description = repositoryModel.description,
                    forksCount = listForks.count(),
                    starCount = listStarGazers.count(),
                    owner = OwnerDataImpl(repositoryModel.owner.avatarUrl, repositoryModel.owner.login),
                    commits = listCommits
                        .map {
                            CommitDataImpl(it.commit.author.name, it.commit.message, it.commit.committer.name)
                        },
                    languages = languagesJson.keySet().toList()
                )
            })
    }

    data class RepositoryDataImpl(
        override val id: Int,
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