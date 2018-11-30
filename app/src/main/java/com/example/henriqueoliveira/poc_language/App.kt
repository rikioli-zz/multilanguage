package com.example.henriqueoliveira.poc_language

import android.app.Application
import android.content.Context


class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "pt"))
    }
}