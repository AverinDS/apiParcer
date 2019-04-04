package com.dmitry.apiparcer.models

import com.dmitry.apiparcer.json.OwnerJson

data class OwnerModel(
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
    constructor(ownerJson: OwnerJson) : this(
        avatarUrl = ownerJson.avatarUrl,
        eventsUrl = ownerJson.eventsUrl,
        followersUrl = ownerJson.followersUrl,
        followingUrl = ownerJson.followingUrl,
        gistsUrl = ownerJson.gistsUrl,
        gravatarId = ownerJson.gravatarId,
        htmlUrl = ownerJson.htmlUrl,
        id = ownerJson.id,
        login = ownerJson.login,
        nodeId = ownerJson.nodeId,
        organizationsUrl = ownerJson.organizationsUrl,
        receivedEventsUrl = ownerJson.receivedEventsUrl,
        reposUrl = ownerJson.reposUrl,
        siteAdmin = ownerJson.siteAdmin,
        starredUrl = ownerJson.starredUrl,
        subscriptionsUrl = ownerJson.subscriptionsUrl,
        type = ownerJson.type,
        url = ownerJson.url
    )
}