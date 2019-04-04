package com.dmitry.apiparcer.models

import com.dmitry.apiparcer.json.ExtendedAuthorJson

data class AuthorModel(
    val avatarUrl: String,
    val eventsUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val gravatarId: String,
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val nodeId: String,
    val organizationsUrl: String,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val type: String,
    val url: String
) {
    constructor(authorJson: ExtendedAuthorJson) : this(
        avatarUrl = authorJson.avatarUrl,
        eventsUrl = authorJson.eventsUrl,
        followersUrl = authorJson.followersUrl,
        followingUrl = authorJson.followingUrl,
        gistsUrl = authorJson.gistsUrl,
        gravatarId = authorJson.gravatarId,
        htmlUrl = authorJson.htmlUrl,
        id = authorJson.id,
        login = authorJson.login,
        nodeId = authorJson.nodeId,
        organizationsUrl = authorJson.organizationsUrl,
        receivedEventsUrl = authorJson.receivedEventsUrl,
        reposUrl = authorJson.reposUrl,
        siteAdmin = authorJson.siteAdmin,
        starredUrl = authorJson.starredUrl,
        subscriptionsUrl = authorJson.subscriptionsUrl,
        type = authorJson.type,
        url = authorJson.url
    )
}