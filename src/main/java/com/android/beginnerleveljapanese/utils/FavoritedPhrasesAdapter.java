package com.android.beginnerleveljapanese.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.beginnerleveljapanese.R;
import com.android.beginnerleveljapanese.data.FavoriteData;
import com.android.beginnerleveljapanese.data.FavoriteDbHelper;
import com.android.beginnerleveljapanese.data.FavoritesContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritedPhrasesAdapter extends RecyclerView.Adapter<FavoritedPhrasesAdapter.PhrasesViewHolder> {

    private static  final String TAG = FavoritedPhrasesAdapter.class.getSimpleName();
    private ArrayList<FavoriteData> favoritedPhrasesArray;
    private Context mContext;
    private int mCount;
    final private favoritedPhrasesAdapterOnClickListener mOnClickListener;
    SQLiteDatabase mDb;

    public FavoritedPhrasesAdapter(Context context, ArrayList<FavoriteData> favorited_phrases_array,
                                   favoritedPhrasesAdapterOnClickListener listener){
        this.mContext = context;
        this.favoritedPhrasesArray = favorited_phrases_array;
        this.mOnClickListener = listener;
    }

    public interface favoritedPhrasesAdapterOnClickListener {
        void onPhraseClicked(int clickedPosition);
    }


    public class PhrasesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.phrases_list_tv)
        TextView phrasesTv;

        @BindView(R.id.phrases_romaji_tv)
        TextView romajiTv;

        @BindView(R.id.favorite_image_button)
        ImageButton favoriteButton;

        @BindView(R.id.phrases_card_view)
        CardView mCardView;

        PhrasesViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);

            //romaji  not visible by default.
            romajiTv.setVisibility(View.GONE);
        }

        //handles click Events when users select a Phrase Category (records position of click).
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onPhraseClicked(clickedPosition);

        }
    }
    @NonNull
    @Override
    public PhrasesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //initialize database variable
        FavoriteDbHelper favoriteDbHelper = new FavoriteDbHelper(mContext);
        mDb = favoriteDbHelper.getWritableDatabase();

        int layoutIdForListItem = R.layout.phrases_list_item;

        boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);


        return new PhrasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhrasesViewHolder holder, final int position) {
        final Boolean[] checkFavorite = new Boolean[1];

        if(favoritedPhrasesArray == null) {
            Log.d(TAG, "phrase_data is null");
            return;
        }

        //gets selected phrase based off array position
        final String current_phrases_english = favoritedPhrasesArray.get(holder.getAdapterPosition()).getFavorited_english_text();
        //gets romaji version of phrase
        final String current_phrase_romaji = favoritedPhrasesArray.get(holder.getAdapterPosition()).getFavorited_romaji_text();
        //Populate views  w/ proper phrases/images.
        holder.phrasesTv.setText(current_phrases_english);
        holder.romajiTv.setText(current_phrase_romaji);

        //null check on holder's favorite status.
        if(favoritedPhrasesArray.get(holder.getAdapterPosition()).getFavorite_boolean() != null){
            //assign holder's current favorite status to checkFavorite
            checkFavorite[0] = favoritedPhrasesArray.get(holder.getAdapterPosition()).getFavorite_boolean();
            Log.v(TAG, String.valueOf(favoritedPhrasesArray.get(holder.getAdapterPosition()).getFavorite_boolean()));

            //Read value of checkFavorite and assign img drawable accordingly
            if (checkFavorite[0]){
                holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite));

            } else {
                holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_border));
            }
        } else{
            Log.v(TAG, "favBool value is null, setting default to false.");
            checkFavorite[0] = false;
            holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_border));
            favoritedPhrasesArray.get(holder.getAdapterPosition()).setFavorite_boolean(checkFavorite[0]);

        }

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFavorite[0]) {

                    deleteFavoritePhrase();
                    //check fav is currently false prior to click, change icon to favorite and
                    // set the checkFav boolean to false.
                    holder.favoriteButton.setImageDrawable(ContextCompat
                            .getDrawable(mContext, R.drawable.ic_favorite_border));

                } else {
                    addFavoritePhrase();
                    //TODO: implement Lottie Animation
                    /**
                     *Should reveal the LottieAnimation View from GONE -> VISIBLE, then play animation once here.
                     */

                    //set boolean value for array
                    favoritedPhrasesArray.get(holder.getAdapterPosition()).setFavorite_boolean(checkFavorite[0]);

                    //check fav is currently true prior to click, change icon to border_fav_icon
                    // (not favorited) and set the favorite state to true.
                    holder.favoriteButton.setImageDrawable(ContextCompat
                            .getDrawable(mContext, R.drawable.ic_favorite));

                }
            }

            /**
             * Helper Method to add the current phrase as a Favorite in the database.
             */
            private void addFavoritePhrase() {
                checkFavorite[0] = true;
                ContentValues addFavPhrase = new ContentValues();
                addFavPhrase.put(FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT, current_phrases_english);
                addFavPhrase.put(FavoritesContract.FavoriteEntry.COLUMN_ROMAJI_TEXT, current_phrase_romaji);
                addFavPhrase.put(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL, String.valueOf(checkFavorite[0]));
                mDb.insert(FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES, null, addFavPhrase);
                Log.d(TAG, "ADDING PHRASE, END BOOL VALUE: " + String.valueOf((addFavPhrase.get(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL))));
            }

            /**
             * Helper method to remove the phrase as a Favorite from database
             */
            private void deleteFavoritePhrase(){
                Log.i(TAG, "DELETING PHRASE, STARTING BOOL VALUE: " + String.valueOf(checkFavorite[0]));
                mDb.delete(FavoritesContract.FavoriteEntry.TABLE_NAME_FAVORITE_PHRASES,
                        FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + "=?",
                        new String[]{current_phrases_english});

                //set boolean to false.
                checkFavorite[0] = false;
                Log.i(TAG, "DELETING PHRASE, END BOOL VALUE: " + String.valueOf(checkFavorite[0]));
            }
        });


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //opens/closes CardView to reveal romaji text.
                if(holder.romajiTv.getVisibility() == View.GONE){
                    holder.romajiTv.setVisibility(View.VISIBLE);
                } else {
                    holder.romajiTv.setVisibility(View.GONE);
                }
            }
        });
    }

    /** Overriding Methods getItemViewType & getItemId and returning 'position' on both prevents
     * recyclerView from re-using/duplicating the same Views.
     * Source:
     * https://stackoverflow.com/questions/33316837/how-to-prevent-items-from-getting-duplicated-when-scrolling-recycler-view
     */

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (null == favoritedPhrasesArray) return 0;

        //populates based off length of Array.
        return favoritedPhrasesArray.size();
    }

    public void notifyPhrasesDataChange(ArrayList<FavoriteData> newFavArray){
        favoritedPhrasesArray = newFavArray;
        notifyDataSetChanged();
    }
}
