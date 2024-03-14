package com.example.learnenglish.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.learnenglish.R;

public class DetailNguPhapActivity extends AppCompatActivity {

    TextView detailDescNP, detailTitleNP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ngu_phap);

        detailDescNP = findViewById(R.id.detailDescNP);
        detailTitleNP = findViewById(R.id.detail_titleNP);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String titleNP = bundle.getString("TitleNP", "DefaultTitle");
            String noidungNP = bundle.getString("NoidungNP", "DefaultNoidung");

            // Sử dụng giá trị mặc định nếu titleNP hoặc noidungNP là null
            detailTitleNP.setText(titleNP);
            detailDescNP.setText(noidungNP);



            if (titleNP != null && noidungNP != null) {
                detailTitleNP.setText(titleNP);
                detailDescNP.setText(noidungNP);
            }
        }

    }
}
