package com.example.sawii00.vocabularyteacher;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class QuizActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Button syn = (Button)findViewById(R.id.quiz_synonyms);
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, SynonymsQuizActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
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
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        switch (id) {

            case R.id.list_menu:
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.quiz_menu:

                break;

            case R.id.add_menu:
                Intent intent2 = new Intent(QuizActivity.this, AddVocabActivity.class);
                startActivity(intent2);
                break;


        }
        return false;
    }
}
