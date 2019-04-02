package com.dmitry.apiparcer.json

import com.google.gson.annotations.SerializedName

data class ExtendedCommitJson(

    @SerializedName("author")
    val author: ExtendedAuthorJson,

    @SerializedName("comments_url")
    val commentsUrl: String,

    @SerializedName("commit")
    val commit: CommitJson,

    @SerializedName("committer")
    val committer: ExtendedCommitterJson,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("sha")
    val sha: String,

    @SerializedName("url")
    val url: String
)