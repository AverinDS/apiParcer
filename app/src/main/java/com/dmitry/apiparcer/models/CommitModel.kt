package com.dmitry.apiparcer.models

import com.dmitry.apiparcer.json.CommitJson
import com.dmitry.apiparcer.json.ExtendedCommitJson

data class CommitModel(
    val author: AuthorModel,
    val commentsUrl: String,
    val htmlUrl: String,
    val nodeId: String,
    val sha: String,
    val url: String,
    val message: String
) {
    constructor(extendedCommitJson: ExtendedCommitJson, commitJson: CommitJson) : this(
        author = AuthorModel(extendedCommitJson.author),
        commentsUrl = extendedCommitJson.commentsUrl,
        htmlUrl = extendedCommitJson.htmlUrl,
        nodeId = extendedCommitJson.nodeId,
        sha = extendedCommitJson.nodeId,
        url = extendedCommitJson.url,
        message = commitJson.message
    )
}