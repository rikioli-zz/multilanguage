package com.example.henriqueoliveira.poc_language;

import android.app.Application;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;

import java.util.Locale;

public class LocaleUtils {

    public static final String LAN_SPANISH      = "es";
    public static final String LAN_PORTUGUESE   = "pt";
    public static final String LAN_ENGLISH      = "en";

    private static Locale sLocale;

    public static void setLocale(Locale locale) {
        sLocale = locale;
        if(sLocale != null) {
            Locale.setDefault(sLocale);
        }
    }

    public static void updateConfig(ContextThemeWrapper wrapper) {
        if(sLocale != null) {
            Configuration configuration = new Configuration();
            configuration.setLocale(sLocale);
            wrapper.applyOverrideConfiguration(configuration);
        }
    }

    public static void updateConfig(Application app, Configuration configuration) {
    }
}