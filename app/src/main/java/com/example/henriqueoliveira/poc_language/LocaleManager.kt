package com.example.henriqueoliveira.poc_language

import android.content.Context
import android.content.res.Configuration
import java.util.*
import android.icu.util.ULocale.getLanguage
import android.os.Build
import android.R.id.edit
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.annotation.SuppressLint
import android.content.res.Resources


object LocaleManager {

    private const val LANGUAGE_ENGLISH = "en"
    private const val LANGUAGE_KEY = "language_key"

    fun setLocale(c: Context): Context {
        return updateResources(c, getLanguage(c))
    }

    fun setNewLocale(c: Context, language: String): Context {
        persistLanguage(c, language)
        return updateResources(c, language)
    }

    private fun getLanguage(c: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(c)
        return prefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(c: Context, language: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(c)
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context, language: String): Context {
        var cont = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        if (Build.VERSION.SDK_INT >= 19) {
            config.setLocale(locale)
            cont = context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return cont
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
    }


}