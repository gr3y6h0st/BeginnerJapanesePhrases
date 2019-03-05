package com.android.beginnerleveljapanese.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.android.beginnerleveljapanese.MainActivity;
import com.android.beginnerleveljapanese.R;
import com.android.beginnerleveljapanese.TranslateResultsActivity;

import java.util.Random;

import butterknife.BindView;

/**
 * Implementation of App Widget functionality.
 */
public class BeginnerJapaneseWidgetProvider extends AppWidgetProvider {

    private final static String TAG = BeginnerJapaneseWidgetProvider.class.getSimpleName();
    private static String[] romajiArray;
    private static String[] romaji_eng_translated_Array;
    private static String randomRomajiPhrase;
    private static String randomEngTranslatedRomaji;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.beginner_japanese_widget);

        Intent remoteViewsServiceIntent = new Intent(context, BeginnerJapaneseRemoteViewsService.class);
        views.setRemoteAdapter(R.id.widget_translator, remoteViewsServiceIntent);

        Intent searchableActivityIntent = new Intent(context, TranslateResultsActivity.class);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);

        PendingIntent pendingSearchablePendingIntent = PendingIntent.getActivity(context, 0, searchableActivityIntent, 0);
        PendingIntent mainActivityPendingIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0 );

        views.setOnClickPendingIntent(R.id.widget_translator, mainActivityPendingIntent);
        views.setOnClickPendingIntent(R.id.widget_translator_search_icon, pendingSearchablePendingIntent);

        views.setTextViewText(R.id.widget_phrases_romaji_tv, randomRomajiPhrase);
        views.setTextViewText(R.id.widget_phrases_list_tv, randomEngTranslatedRomaji);
        views.setImageViewResource(R.id.widget_favorite_image_button, R.drawable.ic_favorite);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //generate random japanese phrase onUpdate.
        generateRandomPhrase(context);

        Log.d(TAG, "onUPDATE");
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        generateRandomPhrase(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     *
     * Helper method that uses Random Class to generate a random japanese phrase (romaji + english)
     * from the xml string array files.
     * @param context used to access resource xml files
     */
    void generateRandomPhrase(Context context){
        //populate arrays
        romajiArray = context.getResources().getStringArray(R.array.accommodation_phrases_romaji);
        romaji_eng_translated_Array = context.getResources().getStringArray(R.array.accommodation_phrases);
        //pull random phrase from arrays w/ randomly generated int
        int phrase_generator = new Random().nextInt(romajiArray.length);
        randomRomajiPhrase = romajiArray[phrase_generator];
        randomEngTranslatedRomaji = romaji_eng_translated_Array[phrase_generator];

    }
}

