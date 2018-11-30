package com.example.henriqueoliveira.poc_language

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_META_DATA
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.example.henriqueoliveira.poc_language.databinding.ActivityMainBinding


class LoginActivity : BaseActivity() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resetTitle()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.en.setOnClickListener {

            LocaleHelper.setLocale(this, "en")
            recreate()
        }

        binding.es.setOnClickListener {

            LocaleHelper.setLocale(this, "es")
            recreate()

        }

        binding.pt.setOnClickListener {

            LocaleHelper.setLocale(this, "pt")
            recreate()
        }

        binding.second.setOnClickListener {

            startActivity(Intent(this, SecondActitivy::class.java))
        }

    }

    private fun resetTitle() {
        try {
            var label = packageManager.getActivityInfo(componentName, GET_META_DATA).labelRes
            if (label != 0) {
                setTitle(label)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.d("fiao", "dsa")
        }
    }
}
