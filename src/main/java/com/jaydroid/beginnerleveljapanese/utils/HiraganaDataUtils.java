package com.jaydroid.beginnerleveljapanese.utils;

import android.content.Context;

import com.jaydroid.beginnerleveljapanese.data.HiraganaData;

import java.util.ArrayList;
import java.util.List;

public class HiraganaDataUtils {
    //private static final String TAG = HiraganaDataUtils.class.getSimpleName();

    public static List<HiraganaData> getHiraganaData(Context context, String[] hiraganaUnicodeArray, String[] hiraganaRomajiTranslation){

        ArrayList<HiraganaData> hiragana_array_list = new ArrayList<>();

        for(int i = 0; i < hiraganaUnicodeArray.length; i++){
            HiraganaData currentHiraganaData = new HiraganaData(hiraganaUnicodeArray[i], hiraganaRomajiTranslation[i]);
            hiragana_array_list.add(currentHiraganaData);
            //Log.v(TAG, hiragana_array_list.get(0).toString());
        }
        return hiragana_array_list;
    }

}
