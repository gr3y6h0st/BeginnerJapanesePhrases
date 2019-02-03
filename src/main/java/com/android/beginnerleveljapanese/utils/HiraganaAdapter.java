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
            String hiragana_syllabary = data[position];
            //populate each category tv w/ proper title.
            holder.hiraganaTv.setText(hiragana_syllabary);

            //Populates Hiragana based on current position of holder
            switch (hiragana_syllabary){

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
                            .load(R.drawable.hiragana_e_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3049":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_o_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u304B":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ka_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u304D":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ki_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u304F":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ku_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u3051":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ke_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3053":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ko_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3055":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_sa_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u3057":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_shi_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u3059":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_su_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u305B":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_se_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;
                case "\u305D":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_so_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u305F":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ta_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3061":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ti_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3064":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_tu_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3066":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_te_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3068":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_to_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                /**
                 *                  "n"
                 *         <item>\u306A</item>
                 *         <item>\u306B</item>
                 *         <item>\u306C</item>
                 *         <item>\u306D</item>
                 *         <item>\u306E</item>
                 */

                case "\u306A":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_na_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u306B":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ni_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u306C":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_nu_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u306D":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ne_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u306E":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_no_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                /**
                 *                  "h"
                 *         <item>\u306F</item>
                 *         <item>\u3072</item>
                 *         <item>\u3075</item>
                 *         <item>\u3078</item>
                 *         <item>\u307B</item>
                 */

                case "\u306F":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ha_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3072":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_hi_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3075":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_fu_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3078":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_he_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u307B":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ho_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                /**
                 *          <-- "m" family -->
                 *         <item>\u307E</item>
                 *         <item>\u307F</item>
                 *         <item>\u3080</item>
                 *         <item>\u3081</item>
                 *         <item>\u3082</item>
                 */

                case "\u307E":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ma_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u307F":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_mi_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3080":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_mu_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3081":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_me_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3082":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_mo_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                /**
                 *          <-- "y" family -->
                 *         <item>\u3084</item>
                 *         <item>\u3086</item>
                 *         <item>\u3088</item>
                 */

                case "\u3084":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ya_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3086":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_yu_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u3088":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_yo_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                /**
                 *          <-- "r" family -->
                 *         <item>\u3089</item>
                 *         <item>\u308A</item>
                 *         <item>\u308B</item>
                 *         <item>\u308C</item>
                 *         <item>\u308D</item>
                 */

                case "\u3089":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ra_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u308A":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ri_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u308B":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ru_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u308C":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_re_stroke_order_animation)
                            .into(holder.hirganaIv);
                    break;

                case "\u308D":
                    Glide.with(mContext)
                            .asGif()
                            .load(R.drawable.hiragana_ro_stroke_order_animation)
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
