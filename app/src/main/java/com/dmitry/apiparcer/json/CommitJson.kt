package com.dmitry.apiparcer.json

import com.google.gson.annotations.SerializedName

data class CommitJson(
    @SerializedName("author")
    val author: AuthorJson,
    @SerializedName("committer")
    val committer: CommitterJson,
    @SerializedName("message")
    val message: String,
    @SerializedName("url")
    val url: String
)