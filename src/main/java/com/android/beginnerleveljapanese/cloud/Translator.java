package com.android.beginnerleveljapanese.cloud;

// Imports the Google Cloud client library

import com.android.beginnerleveljapanese.BuildConfig;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Translator {
    public static String translate(String translateStart) throws Exception {
        // Instantiates a client
        /**
         * TODO: For Reviewers: The follow will require a Google Cloud Translate Api Key. Visit
         * https://cloud.google.com/translate/ to sign up for an API key.
         */
        Translate translate = TranslateOptions.newBuilder()
                .setApiKey(BuildConfig.CLOUD_TRANSLATE_API_KEY).build().getService();
        // The text to translate
        String translatedText = translateStart;

        // Translates some text into Japanese
        Translation translation =
                translate.translate(
                        translatedText,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("ja"));


        System.out.printf("Text: %s%n", translatedText);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
        return translation.getTranslatedText();
    }
}
