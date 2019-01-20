package com.android.beginnerleveljapanese.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContract {

    public static final String CONTENT_AUTHORITY = "com.android.beginnerleveljapanese";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PHRASES = "phrases";

    private static String PATH_UPDATE_DB = null;

    public static final String PATH_FAVORITES = "favorites";

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_PHRASES)
                .build();

        public static final Uri updatePhrasesDb = CONTENT_URI.buildUpon()
                .appendPath(PATH_UPDATE_DB)
                .build();

        public static final Uri buildFavoritesUri = CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITES)
                .build();

        public static final String TABLE_NAME_FAVORITE_PHRASES = "phrases_favorited";
        public static final String COLUMN_ENGLISH_TEXT = "english_text";
        public static final String COLUMN_ROMAJI_TEXT = "romaji_text";
        public static final String COLUMN_FAVORITE_BOOL = "favorite";
        public static final String COLUMN_PHRASE_CATEGORY = "category";
    }
    public static String getUpdatePhrasePath(String eng_text){

        PATH_UPDATE_DB = eng_text;
        return PATH_UPDATE_DB;
    }


}
