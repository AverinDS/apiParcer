package com.dmitry.apiparcer.json

import com.google.gson.annotations.SerializedName

data class AuthorJson(
    @SerializedName("date")
    val date: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)