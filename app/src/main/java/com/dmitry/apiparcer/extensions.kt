package com.dmitry.apiparcer

import android.app.Activity

val Activity.app: App
    get() = application as App