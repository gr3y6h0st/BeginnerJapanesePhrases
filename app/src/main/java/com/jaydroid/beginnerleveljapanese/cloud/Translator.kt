package com.jaydroid.beginnerleveljapanese.cloud

import android.content.Context
import com.google.api.gax.core.CredentialsProvider
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.translate.v3beta1.LocationName
import com.google.cloud.translate.v3beta1.TranslateTextRequest
import com.google.cloud.translate.v3beta1.TranslationServiceClient
import com.google.cloud.translate.v3beta1.TranslationServiceSettings

// Imports the Google Cloud client library
object Translator {
    @JvmStatic
    fun translateText(context: Context, projectId: String?, location: String?,
                      text: String?, sourceLanguageCode: String?,
                      targetLanguageCode: String?): String {
        val credentials: GoogleCredentials
        return try {
            credentials = GoogleCredentials.fromStream(context.assets.open("beginner-japanese-app-ccd49f50eaa2.json"))
            val credentialsProvider: CredentialsProvider = FixedCredentialsProvider.create(credentials)
            val translationServiceSettings = TranslationServiceSettings.newBuilder().setCredentialsProvider(credentialsProvider).build()
            val translationServiceClient = TranslationServiceClient.create(translationServiceSettings)
            val locationName = LocationName.newBuilder().setProject(projectId).setLocation(location).build()
            val translateTextRequest = TranslateTextRequest.newBuilder()
                    .setParent(locationName.toString())
                    .setMimeType("text/plain")
                    .setSourceLanguageCode(sourceLanguageCode)
                    .setTargetLanguageCode(targetLanguageCode)
                    .addContents(text)
                    .build()
            // Call the API
            val response = translationServiceClient.translateText(translateTextRequest)
            response.translationsList[0].translatedText
        } catch (e: Exception) {
            throw RuntimeException("Couldn't create client.", e)
        }
    }
}