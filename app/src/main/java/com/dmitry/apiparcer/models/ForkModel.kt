package com.dmitry.apiparcer.models

import com.dmitry.apiparcer.json.ForkJson

data class ForkModel(
    val archiveUrl: String,
    val archived: Boolean,
    val assigneesUrl: String,
    val blobsUrl: String,
    val branchesUrl: String,
    val cloneUrl: String,
    val collaboratorsUrl: String,
    val commentsUrl: String,
    val commitsUrl: String,
    val compareUrl: String,
    val contentsUrl: String,
    val contributorsUrl: String,
    val createdAt: String,
    val defaultBranch: String,
    val deploymentsUrl: String,
    val description: String,
    val downloadsUrl: String,
    val eventsUrl: String,
    val fork: Boolean,
    val forks: Int,
    val forksCount: Int,
    val forksUrl: String,
    val fullName: String,
    val gitCommitsUrl: String,
    val gitRefsUrl: String,
    val gitTagsUrl: String,
    val gitUrl: String,
    val hasDownloads: Boolean,
    val hasIssues: Boolean,
    val hasPages: Boolean,
    val hasProjects: Boolean,
    val hasWiki: Boolean,
    val homepage: String,
    val hooksUrl: String,
    val htmlUrl: String,
    val id: Int,
    val issueCommentUrl: String,
    val issueEventsUrl: String,
    val issuesUrl: String,
    val isPrivate: Boolean,
    val keysUrl: String,
    val labelsUrl: String,
    val language: String,
    val languagesUrl: String,
    val mergesUrl: String,
    val milestonesUrl: String,
    val name: String,
    val nodeId: String,
    val notificationsUrl: String,
    val openIssues: Int,
    val openIssuesCount: Int,
    val owner: OwnerModel,
    val pullsUrl: String,
    val pushedAt: String,
    val releasesUrl: String,
    val size: Int,
    val sshUrl: String,
    val stargazersCount: Int,
    val stargazersUrl: String,
    val statusesUrl: String,
    val subscribersUrl: String,
    val subscriptionUrl: String,
    val svnUrl: String,
    val tagsUrl: String,
    val teamsUrl: String,
    val treesUrl: String,
    val updatedAt: String,
    val url: String,
    val watchers: Int,
    val watchersCount: Int
) {
    constructor(forkJson: ForkJson) : this(
        archiveUrl = forkJson.archiveUrl,
        archived = forkJson.archived,
        assigneesUrl = forkJson.assigneesUrl,
        blobsUrl = forkJson.blobsUrl,
        branchesUrl = forkJson.branchesUrl,
        cloneUrl = forkJson.cloneUrl,
        collaboratorsUrl = forkJson.collaboratorsUrl,
        commentsUrl = forkJson.commentsUrl,
        commitsUrl = forkJson.commitsUrl,
        compareUrl = forkJson.compareUrl,
        contentsUrl = forkJson.contentsUrl,
        contributorsUrl = forkJson.contributorsUrl,
        createdAt = forkJson.createdAt,
        defaultBranch = forkJson.defaultBranch,
        deploymentsUrl = forkJson.deploymentsUrl,
        description = forkJson.description,
        downloadsUrl = forkJson.downloadsUrl,
        eventsUrl = forkJson.eventsUrl,
        fork = forkJson.fork,
        forks = forkJson.forks,
        forksCount = forkJson.forksCount,
        forksUrl = forkJson.forksUrl,
        fullName = forkJson.fullName,
        gitCommitsUrl = forkJson.gitCommitsUrl,
        gitRefsUrl = forkJson.gitRefsUrl,
        gitTagsUrl = forkJson.gitTagsUrl,
        gitUrl = forkJson.gitUrl,
        hasDownloads = forkJson.hasDownloads,
        hasIssues = forkJson.hasIssues,
        hasPages = forkJson.hasPages,
        hasProjects = forkJson.hasProjects,
        hasWiki = forkJson.hasWiki,
        homepage = forkJson.homepage,
        hooksUrl = forkJson.hooksUrl,
        htmlUrl = forkJson.htmlUrl,
        id = forkJson.id,
        issueCommentUrl = forkJson.issueCommentUrl,
        issueEventsUrl = forkJson.issueEventsUrl,
        issuesUrl = forkJson.issuesUrl,
        isPrivate = forkJson.isPrivate,
        keysUrl = forkJson.keysUrl,
        labelsUrl = forkJson.labelsUrl,
        language = forkJson.language,
        languagesUrl = forkJson.languagesUrl,
        mergesUrl = forkJson.mergesUrl,
        milestonesUrl = forkJson.milestonesUrl,
        name = forkJson.name,
        nodeId = forkJson.nodeId,
        notificationsUrl = forkJson.notificationsUrl,
        openIssues = forkJson.openIssues,
        openIssuesCount = forkJson.openIssuesCount,
        owner = OwnerModel(forkJson.ownerJson),
        pullsUrl = forkJson.pullsUrl,
        pushedAt = forkJson.pushedAt,
        releasesUrl = forkJson.releasesUrl,
        size = forkJson.size,
        sshUrl = forkJson.sshUrl,
        stargazersCount = forkJson.stargazersCount,
        stargazersUrl = forkJson.stargazersUrl,
        statusesUrl = forkJson.statusesUrl,
        subscribersUrl = forkJson.subscribersUrl,
        subscriptionUrl = forkJson.subscriptionUrl,
        svnUrl = forkJson.svnUrl,
        tagsUrl = forkJson.tagsUrl,
        teamsUrl = forkJson.teamsUrl,
        treesUrl = forkJson.treesUrl,
        updatedAt = forkJson.updatedAt,
        url = forkJson.url,
        watchers = forkJson.watchers,
        watchersCount = forkJson.watchersCount
    )
}