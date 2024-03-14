package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.learnenglish.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewActivity extends AppCompatActivity {

    PDFView pdfView;
    TextView itemname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView = findViewById(R.id.pdfView);
        itemname = findViewById(R.id.itemName);

        int postion = getIntent().getIntExtra("position", 0);
        String item = getIntent().getStringExtra("Title");

        if (postion == 0){
            pdfView.fromAsset("HTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 1){
            pdfView.fromAsset("HTTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 2){
            pdfView.fromAsset("HTHT.pdf").load();
            itemname.setText(item);
        }
        if (postion == 3){
            pdfView.fromAsset("HTHTTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 4){
            pdfView.fromAsset("QKD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 5){
            pdfView.fromAsset("QKTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 6){
            pdfView.fromAsset("QKHT.pdf").load();
            itemname.setText(item);
        }
        if (postion == 7){
            pdfView.fromAsset("QKHTTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 8){
            pdfView.fromAsset("TLD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 9){
            pdfView.fromAsset("TLG.pdf").load();
            itemname.setText(item);
        }
        if (postion == 10){
            pdfView.fromAsset("TLTD.pdf").load();
            itemname.setText(item);
        }
        if (postion == 11){
            pdfView.fromAsset("TLHT.pdf").load();
            itemname.setText(item);
        }
        if (postion == 12){
            pdfView.fromAsset("TLHTTD.pdf").load();
            itemname.setText(item);
        }
    }
}