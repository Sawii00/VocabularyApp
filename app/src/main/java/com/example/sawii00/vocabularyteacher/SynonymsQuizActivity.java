package com.example.sawii00.vocabularyteacher;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sawii00.vocabularyteacher.Database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SynonymsQuizActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Map test;
    private TextView word;
    private EditText first_synonym;
    private EditText second_synonym;
    private EditText third_synonym;
    private EditText forth_synonym;
    private EditText fifth_synonym;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synonym_quiz);
        db = new DatabaseHandler(this);
        test = db.returnSynonymQuizArray();
        word = findViewById(R.id.question);
        first_synonym = findViewById(R.id.answer_1);
        second_synonym = findViewById(R.id.answer_2);
        third_synonym = findViewById(R.id.answer_3);
        forth_synonym = findViewById(R.id.answer_4);
        fifth_synonym = findViewById(R.id.answer_5);
        Button insert = findViewById(R.id.confirm_synonym_selection);
        final String[] asked_word = returnValueArray(returnRandomKey());




        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

    public void setLayout(String key, String[] synonyms){
        int n = synonyms.length;
        word.setText(key);
        switch (n){
            case 1:
                second_synonym.setVisibility(View.GONE);
                third_synonym.setVisibility(View.GONE);
                forth_synonym.setVisibility(View.GONE);
                fifth_synonym.setVisibility(View.GONE);
                break;
            case 2:
                third_synonym.setVisibility(View.GONE);
                forth_synonym.setVisibility(View.GONE);
                fifth_synonym.setVisibility(View.GONE);
                break;
            case 3:
                forth_synonym.setVisibility(View.GONE);
                fifth_synonym.setVisibility(View.GONE);
                break;
            case 4:
                fifth_synonym.setVisibility(View.GONE);
                break;
            case 5:

                break;


        }



    }


    public String[] setToArray(Set<String> keySet) {
        String[] keyArray = new String[keySet.size()];
        int counter = 0;

        for (String key : keySet) {
            keyArray[counter] = key;
            counter++;
        }

        return keyArray;
    }

    public String returnRandomKey() {
        Set keySet = test.keySet();
        String[] keyArray = setToArray(keySet);
        Random random = new Random();
        String key = "";
        if (keyArray.length > 1) {
             key = keyArray[random.nextInt(keyArray.length - 1)];
        }else if (keyArray.length == 1){
             key = keyArray[0];
        }else{}
        return key;
    }

    public String[] returnValueArray(String key){
        String[] synonyms = (String[])test.get(key);
        setLayout(key,synonyms);
        return synonyms;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.list_menu:
                Intent intent3 = new Intent(SynonymsQuizActivity.this, MainActivity.class);
                startActivity(intent3);
                break;

            case R.id.quiz_menu:
                Intent intent = new Intent(SynonymsQuizActivity.this, QuizActivity.class);
                startActivity(intent);
                break;

            case R.id.add_menu:
                Intent intent2 = new Intent(SynonymsQuizActivity.this, AddVocabActivity.class);
                startActivity(intent2);
                break;


        }
        return false;
    }


}


