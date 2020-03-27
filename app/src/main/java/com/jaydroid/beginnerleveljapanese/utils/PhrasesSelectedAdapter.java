package com.jaydroid.beginnerleveljapanese.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jaydroid.beginnerleveljapanese.PhrasesCategoryActivity;
import com.jaydroid.beginnerleveljapanese.R;
import com.jaydroid.beginnerleveljapanese.data.FavoriteDbHelper;
import com.jaydroid.beginnerleveljapanese.data.FavoritesContract;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhrasesSelectedAdapter extends RecyclerView.Adapter<PhrasesSelectedAdapter.PhrasesViewHolder> {

    private static final String TAG = PhrasesSelectedAdapter.class.getSimpleName();
    private Context mContext;
    private int mCount;
    private Cursor mCursor;
    final private PhrasesSelectedAdapterOnClickListener mOnClickListener;


    public PhrasesSelectedAdapter(Context context, PhrasesSelectedAdapterOnClickListener listener) {
        this.mContext = context;
        this.mOnClickListener = listener;
    }

    public interface PhrasesSelectedAdapterOnClickListener{
        void markAsFavorite();
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

        PhrasesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            favoriteButton.setOnClickListener(this);
            romajiTv.setVisibility(View.GONE);
            FavoriteDbHelper favoriteDbHelper = new FavoriteDbHelper(mContext);
        }

        //handles click Events when users select a Phrase Category (records position of click).

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public PhrasesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.phrases_list_item;
        boolean shouldAttachToParentImmediately = false;
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new PhrasesViewHolder(view);
    }

    /**
     * Overriding Methods getItemViewType & getItemId and returning 'position' on both prevents
     * recyclerView from re-using/duplicating the same Views.
     * Source:
     * https://stackoverflow.com/questions/33316837/how-to-prevent-items-from-getting-duplicated-when-scrolling-recycler-view
     */

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final PhrasesViewHolder holder, final int position) {
        if (mCursor == null) {
            return;
        } else{
            //move cursor to corresponding adapter position
            mCursor.moveToPosition(holder.getAdapterPosition());
            //gets selected phrase based off array position
            String current_phrases_english = mCursor.getString(PhrasesCategoryActivity.INDEX_ENG_TEXT);
            //gets romaji version of phrase
            String current_phrase_romaji = mCursor.getString(PhrasesCategoryActivity.INDEX_ROMAJI_TEXT);
            //Populate views  w/ proper phrases/images.
            holder.phrasesTv.setText(current_phrases_english);
            holder.romajiTv.setText(current_phrase_romaji);

            //pull favorites boolean value for phrase from db.
            //                favoriteArr.add(mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL)));
            final boolean checkFavorite = Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL)));
            //set icon image based off fav boolean value.
            //Log.i(TAG, "ADAPTER: " + position + " " + holder.getAdapterPosition() + " " + checkFavorite);
            if (!checkFavorite) {
                holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_border));
            } else {
                holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite));
            }
            //click listener
            holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checkFav = checkFavorite;
                    if (checkFav) {
                        holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_border));
                        checkFav = false;
                        updateFavorite(holder.getAdapterPosition(), checkFav);
                        //holder.favoriteButton.setVisibility(View.GONE);
                    } else {
                        holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_favorite));
                        checkFav = true;
                        updateFavorite(holder.getAdapterPosition(), checkFav);
                        mOnClickListener.markAsFavorite();
                    }
                }
            });
        }
        //CardView listener expands/contracts view.
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.romajiTv.getVisibility() == View.GONE) {
                    holder.romajiTv.setVisibility(View.VISIBLE);
                } else {
                    holder.romajiTv.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;

        //populates adapter items based off size of cursor.
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    private void updateFavorite(int position, boolean check_fav) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritesContract.FavoriteEntry.COLUMN_FAVORITE_BOOL,
                String.valueOf(check_fav));

        mCursor.moveToPosition(position);
        mContext.getContentResolver().update(FavoritesContract.FavoriteEntry.CONTENT_URI,
                contentValues,
                FavoritesContract.FavoriteEntry.COLUMN_ENGLISH_TEXT + " = ?",
                new String[] {mCursor.getString(PhrasesCategoryActivity.INDEX_ENG_TEXT)});

    }
}


