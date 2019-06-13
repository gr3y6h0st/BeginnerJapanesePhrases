package com.jaydroid.beginnerleveljapanese.data;

import java.io.Serializable;

public class FavoriteData implements Serializable {

    private String favorited_english_text;
    private String favorited_romaji_text;
    private Boolean favorite_boolean;

    public FavoriteData(){
    }

    public FavoriteData(String favoritedEnglishText, String favoritedRomajiText){
        this.favorited_english_text = favoritedEnglishText;
        this.favorited_romaji_text = favoritedRomajiText;
    }

    public FavoriteData(String favoritedEnglishText, String favoritedRomajiText, Boolean favoriteBoolean){
        this.favorited_english_text = favoritedEnglishText;
        this.favorited_romaji_text = favoritedRomajiText;
        this.favorite_boolean = favoriteBoolean;
    }

    public String getFavorited_english_text() {
        return favorited_english_text;
    }

    public void setFavorited_english_text(String favorited_english_text) {
        this.favorited_english_text = favorited_english_text;
    }

    public String getFavorited_romaji_text() {
        return favorited_romaji_text;
    }

    public void setFavorited_romaji_text(String favorited_romaji_text) {
        this.favorited_romaji_text = favorited_romaji_text;
    }

    public Boolean getFavorite_boolean() {
        return favorite_boolean;
    }

    public void setFavorite_boolean(Boolean favorite_boolean) {
        this.favorite_boolean = favorite_boolean;
    }
}
