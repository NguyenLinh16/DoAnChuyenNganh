package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.learnenglish.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewNguPhapActivity extends AppCompatActivity {

    PDFView pdfViewNP;
    TextView itemTitleNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view_ngu_phap);

        itemTitleNP = findViewById(R.id.itemNameNP);
        pdfViewNP = findViewById(R.id.pdfViewNP);



        int position1 = getIntent().getIntExtra("positionNP", 0);
        String item1 = getIntent().getStringExtra("TitleNP");

        if (item1 != null) {
            itemTitleNP.setText(item1);
        }

        if (position1 == 0){
            pdfViewNP.fromAsset("CBD.pdf").load();
            itemTitleNP.setText(item1);
        }
        if (position1 == 1){
            pdfViewNP.fromAsset("CDK.pdf").load();
            itemTitleNP.setText(item1);
        }
        if (position1 == 2){
            pdfViewNP.fromAsset("MDQH.pdf").load();
            itemTitleNP.setText(item1);
        }
        if (position1 == 3){
            pdfViewNP.fromAsset("CGD.pdf").load();
            itemTitleNP.setText(item1);
        }
        if (position1 == 4){
            pdfViewNP.fromAsset("MDPT.pdf").load();
            itemTitleNP.setText(item1);
        }
        if (position1 == 5){
            pdfViewNP.fromAsset("MDQH.pdf").load();
            itemTitleNP.setText(item1);
        }
    }
}
