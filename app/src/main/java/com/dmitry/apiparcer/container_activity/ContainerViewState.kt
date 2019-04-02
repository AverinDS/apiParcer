package com.dmitry.apiparcer.container_activity

sealed class ContainerViewState {
    data class Hello(val exampleText: String) : ContainerViewState()
    data class Error(val errorMessage: String) : ContainerViewState()
}