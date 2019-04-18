package com.android.beginnerleveljapanese.utils;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beginnerleveljapanese.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesAdapter extends RecyclerView.Adapter<PhrasesAdapter.PhrasesViewHolder> {

    private static  final String TAG = PhrasesAdapter.class.getSimpleName();
    private String[] data;
    private Context mContext;
    private int mCount;
    private TypedArray drawable_array;
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

    @Override
    public void onBindViewHolder(@NonNull PhrasesViewHolder holder, int position) {
        if(data == null) {
            Log.d(TAG, "data is null");
            return;
        }

        //gets category title based off position
        String phrase_category_title = data[position];
        //populate each category tv w/ proper title.
        holder.phrasesTv.setText(phrase_category_title);

        switch (phrase_category_title){

            case "Greetings":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_greetings)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Common":
            //display the images into the holder
            Picasso.get()
                    .load(R.drawable.category_common)
                    .noPlaceholder()
                    .error(R.drawable.ic_sync_black_24dp)
                    .into(holder.phrasesIv);
            break;

            case "Numbers":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_numbers)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case"Emergency":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_emergency)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Shopping":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_shopping)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Eating":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_eating)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Accommodation":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_accommodation)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Weather":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_weather)
                        .placeholder(R.color.colorAccent)
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Places":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_places)
                        .placeholder(R.color.colorAccent)
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;

            case "Transportation":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_transportation)
                        .noPlaceholder()
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;


            case "Time":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_time)
                        .placeholder(R.color.colorAccent)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.phrasesIv);
                break;

            case "Colors":
                //display the images into the holder
                Picasso.get()
                        .load(R.drawable.category_colors)
                        .placeholder(R.color.colorAccent)
                        .error(R.drawable.ic_sync_black_24dp)
                        .into(holder.phrasesIv);
                break;


            default:
                Picasso.get()
                        .load(R.drawable.ic_launcher_background)
                        .placeholder(R.color.colorAccent)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.phrasesIv);
        }
    }

    @Override
    public int getItemCount() {
        if (null == data) return 0;
        return data.length;
    }
}
