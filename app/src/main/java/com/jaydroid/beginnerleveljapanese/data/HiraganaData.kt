package com.jaydroid.beginnerleveljapanese.data

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable

class HiraganaData : Parcelable {
    var hiraganaUnicode: String? = null
    var hiraganaRomajiTranslation: String? = null
    var hiraganaDrawable: Drawable? = null

    constructor() {}
    constructor(hiragana_unicode: String?, hiragana_romaji_translation: String?) {
        hiraganaUnicode = hiragana_unicode
        hiraganaRomajiTranslation = hiragana_romaji_translation
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}
}