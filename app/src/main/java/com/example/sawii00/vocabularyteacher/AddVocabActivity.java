package com.example.sawii00.vocabularyteacher;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sawii00.vocabularyteacher.Database.DatabaseHandler;
import com.example.sawii00.vocabularyteacher.Database.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class AddVocabActivity extends AppCompatActivity {

    private static final String[] categories = {"Noun", "Verb", "Adjective", "Phrasal Verb", "Other"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Spinner spinner = (Spinner) findViewById(R.id.categorySelectionSpinner);
        spinner.bringToFront();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddVocabActivity.this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.insert_button);
        final EditText word_edittext = (EditText) findViewById(R.id.word_edittext);
        final EditText translation_edittext = (EditText) findViewById(R.id.translation_edittext);
        final EditText synonim_edittext = (EditText) findViewById(R.id.synonim_edittext);
        final DatabaseHandler db = new DatabaseHandler(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = word_edittext.getText().toString();
                String translation = translation_edittext.getText().toString().replace(" ","");
                String[] synonyms = synonim_edittext.getText().toString().replace(" ","").split(",");
                String category = spinner.getSelectedItem().toString();

                String[] translation_array = translation.split(",");


                if(!word.isEmpty()&& !translation.isEmpty()){
                    if (synonyms.length==0){
                        //DO STUFF WITHOUT SYNONYM
                        switch (category){
                            case "Noun":
                                db.addNoun(new Word(word, translation));
                                break;
                            case "Verb":
                                db.addVerb(new Word(word, translation));

                                break;

                            case "Adjective":
                                db.addAdjective(new Word(word, translation));

                                break;

                            case "Phrasal Verb":
                                db.addPhrasalVerb(new Word(word, translation));

                                break;

                            case "Other":
                                db.addOther(new Word(word, translation));

                                break;

                        }

                    }else{
                        //DO STUFF WITH SYNONYMS
                        switch (category){
                            case "Noun":
                                db.addNoun(new Word(word, translation,synonyms));
                                break;
                            case "Verb":
                                db.addVerb(new Word(word, translation,synonyms));

                                break;

                            case "Adjective":
                                db.addAdjective(new Word(word, translation,synonyms));

                                break;

                            case "Phrasal Verb":
                                db.addPhrasalVerb(new Word(word, translation,synonyms));

                                break;

                            case "Other":
                                db.addOther(new Word(word, translation,synonyms));

                                break;

                        }

                    }

                    //manage the cleaning ups
                    word_edittext.setText("");
                    synonim_edittext.setText("");
                    translation_edittext.setText("");
                    Toast.makeText(AddVocabActivity.this, "Insertion completed", Toast.LENGTH_SHORT).show();


                }else{

                    Toast.makeText(AddVocabActivity.this, "Insert name and translation", Toast.LENGTH_SHORT).show();

                }

            }
        });

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
                Intent intent = new Intent(AddVocabActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.quiz_menu:
                Intent intent2 = new Intent(AddVocabActivity.this, QuizActivity.class);
                startActivity(intent2);
                break;

            case R.id.add_menu:

                break;


        }
        return false;
    }


}
