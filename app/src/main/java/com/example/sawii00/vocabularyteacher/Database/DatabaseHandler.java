package com.example.sawii00.vocabularyteacher.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db = this.getWritableDatabase();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vocabList";
    public static final String TABLE_NOUNS = "Nouns";
    public static final String TABLE_VERBS = "Verbs";
    public static final String TABLE_ADJECTIVES = "Adjectives";
    public static final String TABLE_PHRASAL_VERBS = "PhrasalVerbs";
    public static final String TABLE_OTHER = "Other";

    public static final String ID = "_id";
    public static final String WORD_KEY = "word";
    public static final String TRANSLATION_KEY = "translation";
    public static final String SYNONIM_KEY = "synonim";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOUNS_TABLE = "CREATE TABLE " + TABLE_NOUNS +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WORD_KEY + " TEXT," +
                TRANSLATION_KEY + " TEXT," +
                SYNONIM_KEY + " TEXT" + ")";
        db.execSQL(CREATE_NOUNS_TABLE);
       String CREATE_VERBS_TABLE = "CREATE TABLE " + TABLE_VERBS +
                "(" + ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
               + WORD_KEY + " TEXT," +
                TRANSLATION_KEY + " TEXT," +
                SYNONIM_KEY + " TEXT" + ")";
        db.execSQL(CREATE_VERBS_TABLE);
        String CREATE_ADJECTIVES_TABLE = "CREATE TABLE " + TABLE_ADJECTIVES +
                "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WORD_KEY + " TEXT," +
                TRANSLATION_KEY + " TEXT," +
                SYNONIM_KEY + " TEXT" + ")";
        db.execSQL(CREATE_ADJECTIVES_TABLE);
        String CREATE_PHRASALVERBS_TABLE = "CREATE TABLE " + TABLE_PHRASAL_VERBS +
                "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WORD_KEY + " TEXT," +
                TRANSLATION_KEY + " TEXT," +
                SYNONIM_KEY + " TEXT" + ")";
        db.execSQL(CREATE_PHRASALVERBS_TABLE);
        String CREATE_OTHER_TABLE = "CREATE TABLE " + TABLE_OTHER +
                "("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WORD_KEY + " TEXT," +
                TRANSLATION_KEY + " TEXT," +
                SYNONIM_KEY + " TEXT" + ")";
        db.execSQL(CREATE_OTHER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOUNS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERBS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADJECTIVES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHRASAL_VERBS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER);

        // Create tables again
        onCreate(db);
    }


    public void addNoun(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_KEY, word.getWord()); // Contact Name
        values.put(TRANSLATION_KEY, word.getTranslation()); // Contact Name
        values.put(SYNONIM_KEY, word.returnString()); // Contact Name
        db.insert(TABLE_NOUNS, null, values);

        db.close(); // Closing database connection


    }

    public void addVerb(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_KEY, word.getWord()); // Contact Name
        values.put(TRANSLATION_KEY, word.getTranslation()); // Contact Name
        values.put(SYNONIM_KEY, word.returnString()); // Contact Name
        db.insert(TABLE_VERBS, null, values);
        db.close(); // Closing database connection


    }

    public void addAdjective(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_KEY, word.getWord()); // Contact Name
        values.put(TRANSLATION_KEY, word.getTranslation()); // Contact Name
        values.put(SYNONIM_KEY, word.returnString()); // Contact Name
        db.insert(TABLE_ADJECTIVES, null, values);
        db.close(); // Closing database connection


    }

    public void addPhrasalVerb(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_KEY, word.getWord()); // Contact Name
        values.put(TRANSLATION_KEY, word.getTranslation()); // Contact Name
        values.put(SYNONIM_KEY, word.returnString()); // Contact Name
        db.insert(TABLE_PHRASAL_VERBS, null, values);
        db.close(); // Closing database connection


    }

    public void addOther(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_KEY, word.getWord()); // Contact Name
        values.put(TRANSLATION_KEY, word.getTranslation()); // Contact Name
        values.put(SYNONIM_KEY, word.returnString()); // Contact Name
        db.insert(TABLE_OTHER, null, values);

        db.close(); // Closing database connection


    }

    public void deleteWord(int id, String type){
        switch (type){
            case "Nouns":
                 db.delete(TABLE_NOUNS, ID + "=" + id, null) ;

            case "Verbs":
                 db.delete(TABLE_VERBS, ID + "=" + id, null) ;
                break;

            case "Adjectives":
                 db.delete(TABLE_ADJECTIVES, ID + "=" + id, null) ;
                break;

            case "Phrasal Verbs":
                 db.delete(TABLE_PHRASAL_VERBS, ID + "=" + id, null) ;
                break;

            case "Other":
                 db.delete(TABLE_OTHER, ID + "=" + id, null) ;
                break;

        }
    }



    public Cursor sortData(String type) {
        Cursor cursor = db.rawQuery("", null);
        if (type == "Nouns") {
            cursor = db.query(TABLE_NOUNS, new String[] {
                    ID,
                    WORD_KEY,
                    TRANSLATION_KEY,
                    SYNONIM_KEY
            }, null, null, null, null, WORD_KEY + " COLLATE NOCASE");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type == "Verbs") {
            cursor = db.query(TABLE_VERBS, new String[] {
                    ID,
                    WORD_KEY,
                    TRANSLATION_KEY,
                    SYNONIM_KEY
            }, null, null, null, null, WORD_KEY + " COLLATE NOCASE");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type == "Adjectives") {
            cursor = db.query(TABLE_ADJECTIVES, new String[] {
                    ID,
                    WORD_KEY,
                    TRANSLATION_KEY,
                    SYNONIM_KEY
            }, null, null, null, null, WORD_KEY + " COLLATE NOCASE");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type == "Phrasal Verbs") {
            cursor = db.query(TABLE_PHRASAL_VERBS, new String[] {
                    ID,
                    WORD_KEY,
                    TRANSLATION_KEY,
                    SYNONIM_KEY
            }, null, null, null, null, WORD_KEY + " COLLATE NOCASE");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type == "Other") {
            cursor = db.query(TABLE_OTHER, new String[] {
                    ID,
                    WORD_KEY,
                    TRANSLATION_KEY,
                    SYNONIM_KEY
            }, null, null, null, null, WORD_KEY + " COLLATE NOCASE");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        }

        return cursor;
    }

    public Map returnSynonymQuizArray(){
        Cursor noun_table_cursor = db.rawQuery("select * from "+TABLE_NOUNS+" where "+SYNONIM_KEY+" != '' ",null);
        Cursor verb_table_cursor = db.rawQuery("select * from "+TABLE_VERBS+" where "+SYNONIM_KEY+" != '' ",null);
        Cursor adjective_table_cursor = db.rawQuery("select * from "+TABLE_ADJECTIVES+" where "+SYNONIM_KEY+" != '' ",null);
        Cursor phrasal_table_cursor = db.rawQuery("select * from "+TABLE_PHRASAL_VERBS+" where "+SYNONIM_KEY+" != '' ",null);
        Cursor other_table_cursor = db.rawQuery("select * from "+TABLE_OTHER+" where "+SYNONIM_KEY+" != '' ",null);
        Map<String,String[]> synonymlist = new HashMap<>();
        try {
            while (noun_table_cursor.moveToNext()) {
                String name = noun_table_cursor.getString(1);
                String[] noun = noun_table_cursor.getString(3).replace(" ","").split(",");
                synonymlist.put(name,noun);
            }
        } finally {
            noun_table_cursor.close();
        }
        try {
            while (verb_table_cursor.moveToNext()) {
                String name = verb_table_cursor.getString(1);
                String[] verb = verb_table_cursor.getString(3).replace(" ","").split(",");
                synonymlist.put(name,verb);
            }
        } finally {
            verb_table_cursor.close();
        }
        try {
            while (adjective_table_cursor.moveToNext()) {
                String name = adjective_table_cursor.getString(1);
                String[] adjective = adjective_table_cursor.getString(3).replace(" ","").split(",");
                synonymlist.put(name,adjective);
            }
        } finally {
            adjective_table_cursor.close();
        }
        try {
            while (phrasal_table_cursor.moveToNext()) {
                String name = phrasal_table_cursor.getString(1);
                String[] phrasal = phrasal_table_cursor.getString(3).replace(" ","").split(",");
                synonymlist.put(name,phrasal);
            }
        } finally {
            phrasal_table_cursor.close();
        }
        try {
            while (other_table_cursor.moveToNext()) {
                String name = other_table_cursor.getString(1);
                String[] other = other_table_cursor.getString(3).replace(" ","").split(",");
                synonymlist.put(name,other);
            }
        } finally {
            other_table_cursor.close();
        }


        return synonymlist;
    }
}
