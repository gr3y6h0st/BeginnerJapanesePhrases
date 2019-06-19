package com.jaydroid.beginnerleveljapanese.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class HiraganaData implements Parcelable {
    private String hiraganaUnicode;
    private String hiraganaRomajiTranslation;
    private Drawable hiraganaDrawable;

    public HiraganaData(){
    }

    public HiraganaData (String hiragana_unicode, String hiragana_romaji_translation){
        this.hiraganaUnicode = hiragana_unicode;
        this.hiraganaRomajiTranslation = hiragana_romaji_translation;
    }

    public String getHiraganaUnicode() {
        return hiraganaUnicode;
    }

    public void setHiraganaUnicode(String hiraganaUnicode) {
        this.hiraganaUnicode = hiraganaUnicode;
    }

    public String getHiraganaRomajiTranslation() {
        return hiraganaRomajiTranslation;
    }

    public void setHiraganaRomajiTranslation(String hiraganaRomajiTranslation) {
        this.hiraganaRomajiTranslation = hiraganaRomajiTranslation;
    }

    public Drawable getHiraganaDrawable() {
        return hiraganaDrawable;
    }

    public void setHiraganaDrawable(Drawable hiraganaDrawable) {
        this.hiraganaDrawable = hiraganaDrawable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
