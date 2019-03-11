package com.android.beginnerleveljapanese.utils;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.android.beginnerleveljapanese.data.FavoritesContract;

import java.util.ArrayList;
import java.util.List;

public class PhraseDataUtils {
    private static final String TAG = PhraseDataUtils.class.getSimpleName();

    private static ContentValues createPhraseContentValues (String phraseCategory, String english_text, String romaji_text, String favoriteStatus) {
        ContentValues phraseDataValues = new ContentValues();
        phraseDataValues.put(FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY, phraseCategory);
        phraseDataValues.put(FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT, english_text);
        phraseDataValues.put(FavoritesContract.FavoriteEntry.COLUMN_ROMAJI_TEXT, romaji_text);
        phraseDataValues.put(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL, favoriteStatus);
        return  phraseDataValues;
    }
    public static void insertNewPhraseData(Context context, String phrase_category, String[] englishTextArr, String[] romajiTextArr){

        List<ContentValues> phraseValues = new ArrayList<ContentValues>();

        for(int i = 0; i < englishTextArr.length; i++){
            phraseValues.add(createPhraseContentValues(phrase_category, englishTextArr[i], romajiTextArr[i], "false"));
            Log.v(TAG, phraseValues.get(0).toString());
        }
        context.getContentResolver().bulkInsert(
                FavoritesContract.FavoriteEntry.CONTENT_URI,
                phraseValues.toArray(new ContentValues[englishTextArr.length]));
    }

}
