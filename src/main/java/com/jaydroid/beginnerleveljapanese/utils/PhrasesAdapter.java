package com.jaydroid.beginnerleveljapanese.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaydroid.beginnerleveljapanese.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesAdapter extends RecyclerView.Adapter<PhrasesAdapter.PhrasesViewHolder> {

    private static  final String TAG = PhrasesAdapter.class.getSimpleName();
    private String[] data;
    private Context mContext;
    private int mCount;
    final private PhrasesAdapterOnClickListener mOnClickListener;


    public PhrasesAdapter(Context context, String[] phrasesData, PhrasesAdapter.PhrasesAdapterOnClickListener listener){
        this.mContext = context;
        this.data = phrasesData;
        this.mOnClickListener = listener;
    }

    public interface PhrasesAdapterOnClickListener {
        void onPhraseCategoryClicked(int clickedPosition);
    }


    public class PhrasesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.phrases_tv)
        TextView phrasesTv;

        @BindView(R.id.phrases_list_iv)
        ImageView phrasesIv;

        PhrasesViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);

        }

        //handles click Events when users select a Phrase Category (records position of click).
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onPhraseCategoryClicked(clickedPosition);
        }
    }
    @NonNull
    @Override
    public PhrasesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        int layoutIdForListItem = R.layout.phrases_categories_list_item;

        boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new PhrasesViewHolder(view);
    }

    /**
     * Helper method to display the images into the holder
     */
    private void displayPicassoImage(final int imageResDrawable, final PhrasesViewHolder holder){
        Picasso.get()
                .load(imageResDrawable)
                .fetch(new Callback(){
                           @Override
                           public void onSuccess() {
                               Picasso.get()
                                       .load(imageResDrawable)
                                       .placeholder(R.color.colorDarkGray)
                                       .error(R.drawable.ic_sync_black_24dp)
                                       .into(holder.phrasesIv);
                           }
                           @Override
                           public void onError(Exception e) {
                               e.printStackTrace();
                           }
                       }
                );
    }

    @Override
    public void onBindViewHolder(@NonNull final PhrasesViewHolder holder, int position) {
        if(data == null) {
            //Log.d(TAG, "data is null");
            return;
        }
        //default image is launcher foreground
        int imageDrawable = R.drawable.ic_launcher_foreground;
        //gets category title based off position
        String phrase_category_title = data[position];
        //populate each category tv w/ proper title.
        holder.phrasesTv.setText(phrase_category_title);

        switch (phrase_category_title){
            case "Greetings":
                imageDrawable = R.drawable.category_greetings;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Common":
                imageDrawable = R.drawable.category_common;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Numbers":
                imageDrawable = R.drawable.category_numbers;
                displayPicassoImage(imageDrawable, holder);
                break;

            case"Emergency":
                imageDrawable = R.drawable.category_emergency;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Shopping":
                imageDrawable = R.drawable.category_shopping;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Eating":
                imageDrawable = R.drawable.category_eating;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Accommodation":
                imageDrawable = R.drawable.category_accommodation;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Weather":
                imageDrawable = R.drawable.category_weather;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Places":
                imageDrawable = R.drawable.category_places;
                displayPicassoImage(imageDrawable, holder);

                break;

            case "Transportation":
                imageDrawable = R.drawable.category_transportation;
                displayPicassoImage(imageDrawable, holder);
                break;


            case "Time":
                imageDrawable = R.drawable.category_time;
                displayPicassoImage(imageDrawable, holder);
                break;

            case "Colors":
                imageDrawable = R.drawable.category_colors;
                displayPicassoImage(imageDrawable, holder);
                break;

            default:
                displayPicassoImage(imageDrawable, holder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (null == data) return 0;
        return data.length;
    }
}
