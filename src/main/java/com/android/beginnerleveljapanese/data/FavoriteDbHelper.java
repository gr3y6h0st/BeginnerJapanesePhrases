package com.android.beginnerleveljapanese.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorite_phrases.db";

    public static final int DATABASE_VERSION = 9995;

    public FavoriteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create table
        final String SQL_CREATE_FAVORITES_MAIN_TABLE =
                "CREATE TABLE " + FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES + " (" +
                        FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + " TEXT PRIMARY KEY," +
                        FavoritesContract.FavoriteEntry.COLUMN_ROMAJI_TEXT + " TEXT, " +
                        FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL + " TEXT, " +
                        FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY + " TEXT, " +
                        " UNIQUE (" + FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + ") ON CONFLICT REPLACE);";

        //execute creation SQL table
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_MAIN_TABLE);
        //"CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDEE +
        //" (" + COLUMN_Att_Event_ID + " TEXT," +
        //COLUMN_Att_Email + " TEXT, PRIMARY KEY(" + COLUMN_Att_Event_ID + "," + COLUMN_Att_Email + "))"
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES);
        onCreate(sqLiteDatabase);

    }
}
