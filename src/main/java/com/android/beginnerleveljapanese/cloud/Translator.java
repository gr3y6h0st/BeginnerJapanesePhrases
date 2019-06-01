package com.android.beginnerleveljapanese.cloud;

// Imports the Google Cloud client library

import android.content.Context;
import android.util.Log;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.v3beta1.LocationName;
import com.google.cloud.translate.v3beta1.TranslateTextRequest;
import com.google.cloud.translate.v3beta1.TranslateTextResponse;
import com.google.cloud.translate.v3beta1.TranslationServiceClient;
import com.google.cloud.translate.v3beta1.TranslationServiceSettings;
import java.io.IOException;


public class Translator {
    public static String translate(Context context, String translateStart, String targetLocale, String sourceLocale) throws IOException {
        /*
        Translate translate = TranslateOptions.newBuilder()
                .setApiKey(BuildConfig.CLOUD_TRANSLATE_API_KEY).build().getService();*/

        //get Service Account instead of API Key.
        GoogleCredentials credentials = GoogleCredentials.fromStream(context.getAssets().open("beginner-japanese-app-70e338ed115f.json"));
        FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);


        Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();

        //Translates some text into Japanese
        Translation translation =
                translate.translate(
                        translateStart,
                        TranslateOption.sourceLanguage(sourceLocale),
                        TranslateOption.targetLanguage(targetLocale));
        Log.i("RESULTS: ", translation.getTranslatedText());
        return translation.getTranslatedText();

    }
    public static String translateText(Context context, String projectId, String location, String text, String sourceLanguageCode, String targetLanguageCode) {

        GoogleCredentials credentials;


        try {
            credentials = GoogleCredentials.fromStream(context.getAssets().open("beginner-japanese-app-70e338ed115f.json"));
            CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);

            TranslationServiceSettings translationServiceSettings = TranslationServiceSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
            TranslationServiceClient translationServiceClient = TranslationServiceClient.create(translationServiceSettings);

            LocationName locationName =
                    LocationName.newBuilder().setProject(projectId).setLocation(location).build();

            TranslateTextRequest translateTextRequest =
                    TranslateTextRequest.newBuilder()
                            .setParent(locationName.toString())
                            .setMimeType("text/plain")
                            .setSourceLanguageCode(sourceLanguageCode)
                            .setTargetLanguageCode(targetLanguageCode)
                            .addContents(text)
                            .build();


            // Call the API
            TranslateTextResponse response = translationServiceClient.translateText(translateTextRequest);
            System.out.format(
                    "Translated Text: %s", response.getTranslationsList().get(0).getTranslatedText());
            return response.getTranslationsList().get(0).getTranslatedText();

        } catch (Exception e) {
            throw new RuntimeException("Couldn't create client.", e);
        }
    }
}
