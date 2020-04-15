package com.jaydroid.beginnerleveljapanese.cloud

import android.content.Context
import com.google.api.gax.core.CredentialsProvider
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.cloud.translate.v3beta1.LocationName
import com.google.cloud.translate.v3beta1.TranslateTextRequest
import com.google.cloud.translate.v3beta1.TranslationServiceClient
import com.google.cloud.translate.v3beta1.TranslationServiceSettings
import com.google.auth.oauth2.GoogleCredentials as GoogleCredentials

object CloudTranslator {
    @JvmStatic
    fun translate(context: Context?, projectId: String?, location: String?,
                      text: String?, sourceLanguageCode: String?,
                      targetLanguageCode: String?): String?
    {
        var credentials: GoogleCredentials

                try {
                    credentials = GoogleCredentials.fromStream(context!!.assets.open("beginner-japanese-app-ccd49f50eaa2.json"))
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
                    return response.translationsList[0].translatedText
                } catch (e: Exception) {
                    throw RuntimeException ("Couldn't create client.", e)
                }

    }
}