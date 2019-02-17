package com.android.beginnerleveljapanese.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.beginnerleveljapanese.R;
import com.android.beginnerleveljapanese.data.HiraganaData;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HiraganaAdapter extends RecyclerView.Adapter<HiraganaAdapter.HiraganaViewHolder> {

    private static  final String TAG = HiraganaAdapter.class.getSimpleName();
    private List<HiraganaData> data;
    private Context mContext;
    private int mCount;
    final private HiraganaAdapterOnClickListener mOnClickListener;


    public HiraganaAdapter(Context context, List<HiraganaData> hiraganaData, HiraganaAdapter.HiraganaAdapterOnClickListener listener){
        this.mContext = context;
        this.data = hiraganaData;
        this.mOnClickListener = listener;
    }

    public interface HiraganaAdapterOnClickListener {
        void onHiraganaSymbolClicked(int clickedPosition);
    }


    public class HiraganaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.hiragana_syllabary_tv)
        TextView hiraganaTv;

        @BindView(R.id.hiragana_card_view)
        CardView mCardView;

        @BindView(R.id.hiragana_list_iv)
        ImageView hiraganaIv;

        @BindView(R.id.hiragana_romaji_tv)
        TextView hiraganaRomajiTv;

        HiraganaViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            hiraganaIv.setVisibility(View.GONE);
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
        final String hiragana_syllabary;
        final String hiragana_syllabary_romaji_translation;

        if(data == null) {
            Log.d(TAG, "data is null");
            return;
        } else {
            Log.d(TAG, "DATA DETECTED" + " " + data.get(position).getHiraganaUnicode());

            //gets category title based off position
            hiragana_syllabary = data.get(position).getHiraganaUnicode();
            hiragana_syllabary_romaji_translation = data.get(position).getHiraganaRomajiTranslation();

            //populate each category tv w/ proper data.
            if(!hiragana_syllabary_romaji_translation.equals("placeholder")){
                holder.hiraganaTv.setText(hiragana_syllabary);
                holder.hiraganaRomajiTv.setText(hiragana_syllabary_romaji_translation);
            } else{
                holder.mCardView.setVisibility(View.INVISIBLE);
            }

        }
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            MediaPlayer hiraganaMediaPlayer;

            @Override
            public void onClick(View view) {
                switch (hiragana_syllabary_romaji_translation){
                    case "a":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.a);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "i":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.i);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "u":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.u);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "e":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.e);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "o":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.o);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ka":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ka);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ki":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ki);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ku":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ku);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ke":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ke);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ko":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ko);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ha":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ha);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "hi":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.hi);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "fu":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.fu);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "he":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.he);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ho":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ho);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ma":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ma);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "mi":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.mi);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "mu":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.mu);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "me":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.me);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "mo":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.mo);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "na":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.na);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ni":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ni);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "nu":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.nu);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ne":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ne);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "no":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.no);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ra":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ra);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ri":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ri);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ru":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ru);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "re":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.re);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ro":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ro);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "sa":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.sa);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "shi":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.shi);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "su":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.su);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "se":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.se);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "so":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.so);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ta":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ta);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "chi":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.chi);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "tsu":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.tsu);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "te":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.te);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "to":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.to);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "wa":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.wa);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "wo":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.wo);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "ya":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.ya);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "yo":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.yo);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "yu":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.yu);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    case "n":
                        hiraganaMediaPlayer = MediaPlayer.create(mContext, R.raw.n);
                        hiraganaMediaPlayer.seekTo(2000);
                        hiraganaMediaPlayer.start();
                        break;

                    default:
                        Log.v(TAG, hiragana_syllabary_romaji_translation);
                        hiraganaMediaPlayer= MediaPlayer.create(mContext, R.raw.a);
                        hiraganaMediaPlayer.start();
                        break;
                }
            }
        });

        /**
         * CardView OnLongClickListener reveals Stroke Order
         * for each Hiragana item using an AlertDialog
         **/
        holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                LayoutInflater factory = LayoutInflater.from(mContext);
                view = factory.inflate(R.layout.image_preview, null);
                ImageView gifImageView = view.findViewById(R.id.hiragana_popup_dialog_image_view);
                AlertDialog.Builder share_dialog = new AlertDialog.Builder(mContext);
                share_dialog.setNegativeButton("Close", null);
                share_dialog.setView(view);

                //Populates Hiragana based on current position of holder
                switch (hiragana_syllabary) {

                    case "\u3042":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_a_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3044":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_i_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3046":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_u_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3048":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_e_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3049":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_o_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u304B":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ka_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u304D":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ki_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u304F":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ku_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u3051":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ke_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3053":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ko_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3055":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_sa_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u3057":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_shi_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u3059":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_su_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u305B":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_se_stroke_order_animation)
                                .into(gifImageView);
                        break;
                    case "\u305D":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_so_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u305F":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ta_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3061":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ti_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3064":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_tu_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3066":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_te_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3068":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_to_stroke_order_animation)
                                .into(gifImageView);
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
                                .into(gifImageView);
                        break;

                    case "\u306B":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ni_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u306C":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_nu_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u306D":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ne_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u306E":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_no_stroke_order_animation)
                                .into(gifImageView);
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
                                .into(gifImageView);
                        break;

                    case "\u3072":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_hi_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3075":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_fu_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3078":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_he_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u307B":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ho_stroke_order_animation)
                                .into(gifImageView);
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
                                .into(gifImageView);
                        break;

                    case "\u307F":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_mi_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3080":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_mu_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3081":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_me_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3082":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_mo_stroke_order_animation)
                                .into(gifImageView);
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
                                .into(gifImageView);
                        break;

                    case "\u3086":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_yu_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3088":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_yo_stroke_order_animation)
                                .into(gifImageView);
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
                                .into(gifImageView);
                        break;

                    case "\u308A":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ri_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u308B":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ru_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u308C":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_re_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u308D":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_ro_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    /**
                     *              W-family
                     *        <item>\u308F</item>
                     *         <item>\u3090</item>
                     *         <item>\u3091</item>
                     *         <item>\u3092</item>
                     */


                    case "\u308F":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_wa_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    case "\u3090":
                        Glide.with(mContext)
                                .load(R.drawable.hiragana_wi_stroke_order_image)
                                .into(gifImageView);
                        break;

                    case "\u3091":
                        Glide.with(mContext)
                                .load(R.drawable.hiragana_we_stroke_order_image)
                                .into(gifImageView);
                        break;

                    case "\u3092":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_wo_stroke_order_animation)
                                .into(gifImageView);
                        break;

                    /**
                     * Misc characters: "n"
                     */
                    case "\u3093":
                        Glide.with(mContext)
                                .asGif()
                                .load(R.drawable.hiragana_n_stroke_order_animation)
                                .into(gifImageView);
                        break;
                }
                //time to show the dialog!
                share_dialog.show();

                return false;

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
        return data.size();
    }
}
