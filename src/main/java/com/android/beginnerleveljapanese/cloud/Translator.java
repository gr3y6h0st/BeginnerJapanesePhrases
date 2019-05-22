package com.android.beginnerleveljapanese.cloud;

// Imports the Google Cloud client library

import android.content.res.AssetManager;
import android.content.res.Resources;

import com.android.beginnerleveljapanese.BuildConfig;
import com.android.beginnerleveljapanese.R;
import com.android.beginnerleveljapanese.TranslateResultsActivity;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Translator {
    public static String translate(String translateStart, String targetLocale, String sourceLocale) {

        // Instantiates a client
        /**
         * TODO: For Reviewers: The follow will require a Google Cloud Translate Api Key. Visit
         * https://cloud.google.com/translate/ to sign up for an API key.
         */
        Translate translate = TranslateOptions.newBuilder()
                .setApiKey(BuildConfig.CLOUD_TRANSLATE_API_KEY).build().getService();
        // Translates some text into Japanese
        Translation translation =
                translate.translate(
                        translateStart,
                        TranslateOption.sourceLanguage(sourceLocale),
                        TranslateOption.targetLanguage(targetLocale));
        return translation.getTranslatedText();
    }
}
