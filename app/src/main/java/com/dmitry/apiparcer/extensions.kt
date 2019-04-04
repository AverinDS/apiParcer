package com.dmitry.apiparcer

import android.app.Activity

val Activity.app: App
    get() = application as App

val String.withoutTopDomain: String
    get() = this.replace(BuildConfig.BASE_URL, "")

val String.withoutShaArg: String
    get() = this.replace("{/sha}", "")