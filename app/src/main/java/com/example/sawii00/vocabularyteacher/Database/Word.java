package com.example.sawii00.vocabularyteacher.Database;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;

public class Word {
    String word;
    String translation;
    ArrayList<String> synonyms = new ArrayList<>();

    public Word(String word, String translation, String[] synonim ) {
        this.word = word;
        for (String s: synonim) {
            synonyms.add(s);
        }
        this.translation = translation;

    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;

    }
    public String returnString(){

        String listString = TextUtils.join(", ", synonyms);
        return listString;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public ArrayList getSynonims() {
        return synonyms;
    }

    public void addSynonym(String synonim) {
        synonyms.add(synonim);
    }
}
