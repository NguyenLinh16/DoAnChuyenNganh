package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.widget.TextView;

import com.example.learnenglish.R;

public class DetailCacThiTAActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cac_thi_ta_activity);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detail_Title);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("Title");
            String noidung = bundle.getString("Noidung");

            if (title != null && noidung != null) {
                detailTitle.setText(title);
                detailDesc.setText(noidung);
            }
        }
    }

}