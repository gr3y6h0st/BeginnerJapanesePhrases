package com.android.beginnerleveljapanese.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beginnerleveljapanese.R;
import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HiraganaAdapter extends RecyclerView.Adapter<HiraganaAdapter.HiraganaViewHolder> {

    private static  final String TAG = HiraganaAdapter.class.getSimpleName();
    private String[] data;
    private Context mContext;
    private int mCount;
    final private HiraganaAdapterOnClickListener mOnClickListener;


    public HiraganaAdapter(Context context, String[] hiraganaData, HiraganaAdapter.HiraganaAdapterOnClickListener listener){
        this.mContext = context;
        this.data = hiraganaData;
        this.mOnClickListener = listener;
    }

    public interface HiraganaAdapterOnClickListener {
        void onHiraganaSymbolClicked(int clickedPosition);
    }


    public class HiraganaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.hiraganaTv)
        TextView hiraganaTv;

        @BindView(R.id.hiragana_list_iv)
        ImageView hirganaIv;

        @BindView(R.id.hiragana_card_view)
        CardView mCardView;

        HiraganaViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            hirganaIv.setVisibility(View.GONE);
        }

        //handles click Events when users select a Phrase Category (records position of click).
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onHiraganaSymbolClicked(clickedPosition);
        }
    }
    @NonNull
    @Override
    public HiraganaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        int layoutIdForListItem = R.layout.hirgana_syllabary_list_item;

        boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new HiraganaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HiraganaViewHolder holder, int position) {
        if(data == null) {
            Log.d(TAG, "data is null");
            return;
        } else {
            Log.d(TAG, "DATA DETECTED" + " " + data[position]);

            //gets category title based off position
            String hiragana_category_title = data[position];
            //populate each category tv w/ proper title.
            holder.hiraganaTv.setText(hiragana_category_title);


            switch (hiragana_category_title){

                case "\u3042":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_a_stroke_order_animation)
                            .into(holder.hirganaIv);

                    break;

                case "\u3044":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_i_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3046":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_u_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;


                case "\u3048":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_a_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
            }
        }

        //CardView listener expands/contracts CardView's Image.
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.hirganaIv.getVisibility() == View.GONE) {
                    holder.hirganaIv.setVisibility(View.VISIBLE);
                } else {
                    holder.hirganaIv.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (null == data) return 0;
        return data.length;
    }
}
