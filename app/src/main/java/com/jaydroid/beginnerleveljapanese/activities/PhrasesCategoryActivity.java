package com.jaydroid.beginnerleveljapanese.activities;

import android.animation.Animator;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.jaydroid.beginnerleveljapanese.R;
import com.jaydroid.beginnerleveljapanese.data.FavoriteDbHelper;
import com.jaydroid.beginnerleveljapanese.data.FavoritesContract;
import com.jaydroid.beginnerleveljapanese.fragments.MainActivityFragment;
import com.jaydroid.beginnerleveljapanese.utils.PhraseDataUtils;
import com.jaydroid.beginnerleveljapanese.utils.PhrasesSelectedAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesCategoryActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>,
        PhrasesSelectedAdapter.PhrasesSelectedAdapterOnClickListener {

    private static final String TAG = PhrasesCategoryActivity.class.getSimpleName();
    private static final int ID_PHRASES_SELECTED_LOADER = 919;
    private static final int ID_RESTORE_LOADER = 929;

    public String[] mPhrases;
    public String[] mRomaji;
    public ArrayList<String> favoriteArr = new ArrayList<>();

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
    @BindView(R.id.favorite_animation_view)
    LottieAnimationView favoriteAnimationView;
    RecyclerView.LayoutManager phrasesLayoutManager;
    PhrasesSelectedAdapter mPhrasesSelectedAdapter;
    private int mPosition = RecyclerView.NO_POSITION;
    private Cursor mCursor;
    private String mCategoryTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases_category);
        ButterKnife.bind(this);
        favoriteAnimationView.setVisibility(View.GONE);

        mCategoryTitle = getIntent().getStringExtra(MainActivityFragment.PHRASE_CATEGORY_SELECTED);
        mPhrases = getIntent().getStringArrayExtra(MainActivityFragment.PHRASE_STRING_ARRAY);
        mRomaji = getIntent().getStringArrayExtra(MainActivityFragment.PHRASE_ROMAJI_TRANSLATION);

        String formatted_activity_title = mCategoryTitle + " Phrases";
        setTitle(formatted_activity_title);

        if(isDatabaseEmpty(FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES)){
            PhraseDataUtils.insertNewPhraseData(this, mCategoryTitle, mPhrases, mRomaji);
            //Log.i(TAG, "CURSOR NULL @ onCREATE, calling insertPhraseData.");
            getSupportLoaderManager().initLoader(ID_PHRASES_SELECTED_LOADER, null, this);
        } else{
            getSupportLoaderManager().initLoader(ID_RESTORE_LOADER, null, this);
        }
        //create Adapter instance and set it on RecyclerView using LinearLayoutManager
        mPhrasesSelectedAdapter = new PhrasesSelectedAdapter(this, this);
        phrasesLayoutManager = new LinearLayoutManager(this);
        phrases_category_rv.setLayoutManager(phrasesLayoutManager);
        phrases_category_rv.setHasFixedSize(true);
        phrases_category_rv.setAdapter(mPhrasesSelectedAdapter);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, @Nullable Bundle args) {

        switch(loaderId){

            case ID_PHRASES_SELECTED_LOADER:
                Uri phrase_query_uri = FavoritesContract.FavoriteEntry.CONTENT_URI;

                return new CursorLoader(
                        getApplicationContext(),
                        phrase_query_uri,
                        PHRASES_CATEGORY_PROJECTION,
                        FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY + " = ? ",
                        new String[] {mCategoryTitle},
                        FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + " ASC");

            case ID_RESTORE_LOADER:

                Uri favoritesQueryUri = FavoritesContract.FavoriteEntry.CONTENT_URI;

                return new CursorLoader(
                        getApplicationContext(),
                        favoritesQueryUri,
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
        if(data != null) {
            mCursor = data;
            mPhrasesSelectedAdapter.swapCursor(mCursor);
        }
        //Log.v(TAG + " onLoaderFinished: " + loader.getId(), DatabaseUtils.dumpCursorToString(data));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
       //Log.v(TAG + " " + loader.getId(), "DESTROYING LOADER.");
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save Data
        if(mCursor != null){
            mCursor.moveToFirst();
            while(mCursor.moveToNext()){
                favoriteArr.add(mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL)));
                /*Log.i(TAG, "SAVEDINSTANCE LOADING UP FAVORITE ARRAY" + "\n" +
                        mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL)));*/
            }
        }
        //Save RV_Adapter Position
        Parcelable mSavedStatePosition = phrases_category_rv.getLayoutManager().onSaveInstanceState();
        outState.putParcelable("recyclerViewPosition", mSavedStatePosition);
        outState.putStringArrayList("favoriteArray", favoriteArr);
        outState.putStringArray("englishPhrases", mPhrases);
        outState.putStringArray("romajiPhrases", mRomaji);
        outState.putString("categoryTitle", mCategoryTitle);
    }

    /**
     * Helper Method that checks to see if the current phrase category is present w/in
     * the local SQLite Db.
     * @param tableName
     * @return
     */
    public boolean isDatabaseEmpty(String tableName){
        FavoriteDbHelper favoriteDbHelper = new FavoriteDbHelper(getApplicationContext());

        SQLiteDatabase database = favoriteDbHelper.getReadableDatabase();
        //check database by CATEGORY.
        int numberOfRows = (int) DatabaseUtils.queryNumEntries(database,tableName,
                FavoritesContract.FavoriteEntry.COLUMN_PHRASE_CATEGORY + " = ? ",
                new String[]{mCategoryTitle});

        return numberOfRows == 0;
    }

    @Override
    public void markAsFavorite() {

        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                favoriteAnimationView.setVisibility(View.VISIBLE);
                favoriteAnimationView.bringToFront();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                favoriteAnimationView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
        favoriteAnimationView.addAnimatorListener(listener);
        favoriteAnimationView.playAnimation();
    }
}
