package com.example.learnenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;
import com.example.learnenglish.model.Vocabulary;

public class SelectLevelActivity extends AppCompatActivity {

    private Vocabulary vocabulary;
    Button btnEasy,btnNormal,btnHard, btnAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        btnEasy = findViewById(R.id.btDe);
        btnNormal = findViewById(R.id.btTb);
        btnHard = findViewById(R.id.btKho);
        //btnAll = findViewById(R.id.btTatCa);
        getIncomingData();
        //Toast.makeText(this, vocabulary.getNameVocabulary(), Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(vocabulary.getNameVocabulary());
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLevelActivity.this,VocabularyItemActivity.class);
                intent.putExtra("position",vocabulary);
                intent.putExtra("level",0);
                startActivity(intent);
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLevelActivity.this,VocabularyItemActivity.class);
                intent.putExtra("position",vocabulary);
                intent.putExtra("level",1);
                startActivity(intent);
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLevelActivity.this,VocabularyItemActivity.class);
                intent.putExtra("position",vocabulary);
                intent.putExtra("level",2);
                startActivity(intent);
            }
        });
    }
    private void getIncomingData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("position")) {
                vocabulary = (Vocabulary) intent.getSerializableExtra("position");
                //Toast.makeText(this, vocabulary.getNameVocabulary(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
