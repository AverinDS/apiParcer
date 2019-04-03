package com.dmitry.apiparcer.container_activity

sealed class ContainerViewState {
    object LoadSuccess : ContainerViewState()
    data class Error(val errorMessage: String) : ContainerViewState()
}