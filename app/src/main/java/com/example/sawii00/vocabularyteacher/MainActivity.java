package com.example.sawii00.vocabularyteacher;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sawii00.vocabularyteacher.Database.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String[] vocabs = {"Nouns", "Verbs", "Adjectives", "Phrasal Verbs", "Other"};
    private DatabaseHandler db;
    private SimpleCursorAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        Spinner spinner = (Spinner) findViewById(R.id.vocabSpinnerSelection);
        spinner.bringToFront();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, vocabs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        sortAndShow("Nouns");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {

            case 0:
                String type = "Nouns";
                sortAndShow("Nouns");

                break;

            case 1:
                String type1 = "Verbs";
                sortAndShow("Verbs");

                break;
            case 2:
                String type2 = "Adjectives";
                sortAndShow("Adjectives");

                break;
            case 3:
                String type3 = "Phrasal Verbs";
                sortAndShow("Phrasal Verbs");

                break;
            case 4:
                String type4 = "Other";
                sortAndShow("Other");

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void sortAndShow(final String cat) {
        Cursor cursor = db.sortData(cat);

        String[] columns = new String[] {
                DatabaseHandler.ID,
                DatabaseHandler.WORD_KEY,
                DatabaseHandler.SYNONIM_KEY,
                DatabaseHandler.TRANSLATION_KEY
        };

        int[] to = new int[] {
                R.id.useless,
                R.id.word_value,
                R.id.synonym_value,
                R.id.translation_value,

        };
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.vocab_list_layout,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.vocablist);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cur = (Cursor)dataAdapter.getItem(position);
                cur.moveToPosition(position);
                int id_ = cur.getInt(cur.getColumnIndexOrThrow("_id"));
                db.deleteWord(id_,cat);
                Toast.makeText(MainActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
                sortAndShow(cat);
                return false;
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

                break;

            case R.id.quiz_menu:
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
                break;

            case R.id.add_menu:
                Intent intent2 = new Intent(MainActivity.this, AddVocabActivity.class);
                startActivity(intent2);
                break;


        }
        return false;
    }
}
