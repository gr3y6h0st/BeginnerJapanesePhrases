package com.android.beginnerleveljapanese;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.beginnerleveljapanese.data.FavoritesContract;
import com.android.beginnerleveljapanese.utils.PhraseDataUtils;
import com.android.beginnerleveljapanese.utils.PhrasesSelectedAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.beginnerleveljapanese.MainActivityFragment.PHRASE_CATEGORY_SELECTED;
import static com.android.beginnerleveljapanese.MainActivityFragment.PHRASE_ERROR_CHECK;
import static com.android.beginnerleveljapanese.MainActivityFragment.PHRASE_ROMAJI_TRANSLATION;
import static com.android.beginnerleveljapanese.MainActivityFragment.PHRASE_STRING_ARRAY;

public class PhrasesCategoryActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = PhrasesCategoryActivity.class.getSimpleName();
    private static final int ID_PHRASES_SELECTED_LOADER = 919;
    public String[] mPhrases;
    public String[] mRomaji;

    public static final String[] PHRASES_CATEGORY_PROJECTION = {
            FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT,
            FavoritesContract.FavoriteEntry.COLUMN_ROMAJI_TEXT,
            FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL,
            FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY,
    };
    public static final int INDEX_ENG_TEXT = 0;
    public static final int INDEX_ROMAJI_TEXT = 1;
    public static final int INDEX_BOOL_VALUE = 2;
    public static final int INDEX_PHRASE_CATEGORY = 3;

    @BindView(R.id.phrases_category_rv)
    RecyclerView phrases_category_rv;
    RecyclerView.LayoutManager phrasesLayoutManager;
    PhrasesSelectedAdapter mPhrasesSelectedAdapter;
    private int mPosition = RecyclerView.NO_POSITION;
    private Cursor mCursor;
    private String mCategoryTitle;
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases_category);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            mCategoryTitle = getIntent().getStringExtra(PHRASE_CATEGORY_SELECTED);
            String formatted_activity_title = mCategoryTitle + " Phrases";
            mPhrases = getIntent().getStringArrayExtra(PHRASE_STRING_ARRAY);
            mRomaji = getIntent().getStringArrayExtra(PHRASE_ROMAJI_TRANSLATION);
            if (mPhrases == null) {
                Log.v(TAG, getIntent().getStringExtra(PHRASE_ERROR_CHECK));
            }
            setTitle(formatted_activity_title);

            if(mCursor == null){
                //TODO: ADD Category Title COLUMN to Database.
                //POPULATE DATABASE W/ CURRENT PHRASE CATEGORY DATA
                PhraseDataUtils.insertPhraseData(this, mCategoryTitle, mPhrases, mRomaji);
                getSupportLoaderManager().initLoader(ID_PHRASES_SELECTED_LOADER, null, this);
            } else {
                mCursor.moveToFirst();
                if (mCursor.getString(INDEX_PHRASE_CATEGORY).equals(mCategoryTitle)){
                    getSupportLoaderManager().restartLoader(ID_PHRASES_SELECTED_LOADER, null, this);
                }
            }

            //create Adapter instance and set it on RecyclerView using LinearLayoutManager
            mPhrasesSelectedAdapter = new PhrasesSelectedAdapter(this);
            phrasesLayoutManager = new LinearLayoutManager(this);
            phrases_category_rv.setLayoutManager(phrasesLayoutManager);
            phrases_category_rv.setHasFixedSize(true);
            phrases_category_rv.setAdapter(mPhrasesSelectedAdapter);
        } else {
            Log.v(TAG, "CHECK INTENT BUNDLE. IT MAY BE EMPTY/NULL.");
        }

    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, @Nullable Bundle args) {

        switch(loaderId){

            case ID_PHRASES_SELECTED_LOADER:

                Uri phrase_query_uri = FavoritesContract.FavoriteEntry.CONTENT_URI;

                return new CursorLoader(this,
                        phrase_query_uri,
                        PHRASES_CATEGORY_PROJECTION,
                        FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY + " = ? ",
                        new String[] {mCategoryTitle},
                        FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + " ASC");

            default:
                throw new RuntimeException("Loader not implemented: "  + loaderId);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursor = data;
        mPhrasesSelectedAdapter.swapCursor(mCursor);
        if(mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        Log.v(TAG + " onLoaderFinished: " + String.valueOf(loader.getId()), DatabaseUtils.dumpCursorToString(data));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.v(TAG + " " + String.valueOf(loader.getId()), "DESTROYING LOADER.");
        mPhrasesSelectedAdapter.swapCursor(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int menuItemClicked = item.getItemId();

        if (menuItemClicked == R.id.action_settings) {
            Intent settingsActivityIntent = new Intent(PhrasesCategoryActivity.this, SettingsActivity.class);
            startActivity(settingsActivityIntent);
            return true;
        } else if (menuItemClicked == R.id.menu_search_icon){
            onSearchRequested();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
