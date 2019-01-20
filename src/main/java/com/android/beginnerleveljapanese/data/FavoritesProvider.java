package com.android.beginnerleveljapanese.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class FavoritesProvider extends ContentProvider {

    final String TAG = FavoritesProvider.class.getSimpleName();
    public static final int CODE_PHRASES = 519;
    public static final int CODE_FAVORITE = 619;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private FavoriteDbHelper mOpenHelper;

    public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FavoritesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, FavoritesContract.PATH_PHRASES, CODE_PHRASES);
        matcher.addURI(authority, FavoritesContract.PATH_PHRASES + "/*", CODE_FAVORITE);


        return matcher;
    }
    @Override
    public boolean onCreate() {

        mOpenHelper = new FavoriteDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        switch (sUriMatcher.match(uri)){

            case CODE_PHRASES: {
                //QUERY ALL OF THE DATA TO DISPLAY
                cursor = db.query(
                        FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }

            case CODE_FAVORITE: {

                //QUERY DATA MARKED AS FAVORITE (Fav column as selection + marked for "true").
                cursor = db.query(
                        FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
    @Nullable
    @Override
    public int bulkInsert(@NonNull Uri uri, @Nullable ContentValues[] contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case CODE_PHRASES:
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : contentValues) {

                        long _id = db.insert(FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;

            default:
                return super.bulkInsert(uri, contentValues);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri retUri = null;
        long id;

        switch (sUriMatcher.match(uri)){

            case CODE_FAVORITE:

                db.beginTransaction();
                //insert Favorite Phrase
                try{
                    id = db.insert(FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES, null, contentValues);

                    if(id != -1) {
                        retUri = ContentUris.withAppendedId(FavoritesContract.FavoriteEntry.CONTENT_URI, id);

                        System.out.println("Successful insert!");
                    } else {
                        System.out.println("SORRY INSERT FAILED!");
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                break;
        }
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted = 0;
        if(null == selection) selection = "1";

        switch (sUriMatcher.match(uri)){

            case CODE_PHRASES:
                String id = uri.getLastPathSegment();
                Log.d(TAG, id);
                numRowsDeleted = mOpenHelper.getWritableDatabase().delete(
                        FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES,
                        selection,
                        selectionArgs);
                    break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();


        if (null == selection) selection = "1";
        //selectionArgs = new String[]{""};

        switch (sUriMatcher.match(uri)) {


            case CODE_PHRASES:

                rowsUpdated = db.update(
                        FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES,
                        contentValues,
                        FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + " = ?",
                        selectionArgs
                );
                break;
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
