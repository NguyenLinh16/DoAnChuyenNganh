package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.learnenglish.R;
import com.example.learnenglish.adapter.CacThiTrongTAAdapter;
import com.example.learnenglish.model.CacThiTA;

import java.util.ArrayList;
import java.util.List;

public class CacThiTrongTAActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CacThiTrongTAAdapter cacThiTrongTAAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cac_thi_trong_ta_activity);

        //Create and set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        cacThiTrongTAAdapter = new CacThiTrongTAAdapter(dataList(),this);
        recyclerView.setAdapter(cacThiTrongTAAdapter);


    }
    //create list of data
    private List<CacThiTA> dataList() {
        List<CacThiTA> list = new ArrayList<>();
        list.add(new CacThiTA("Thì Hiện Tại Đơn", "Present Simple"));
        list.add(new CacThiTA("Thì Hiện Tại Tiếp Diễn", "Present Continuous"));
        list.add(new CacThiTA("Thì Hiện Tại Hoàn Thành", "Present Perfect"));
        list.add(new CacThiTA("Thì Hiện Tại Hoàn Thành Tiếp Diễn", "Present Perfect Continuous"));

        list.add(new CacThiTA("Thì Quá Khứ Đơn", "Past Simple"));
        list.add(new CacThiTA("Thì Quá Khứ Tiếp Diễn", "Past Continuous"));
        list.add(new CacThiTA("Thì Quá Khứ Hoàn Thành", "Past Perfect"));
        list.add(new CacThiTA("Thì Quá Khứ Hoàn Thành Tiếp Diễn", "PPast Perfect Continuous"));

        list.add(new CacThiTA("Thì Tương Lai Đơn", "Future Simple"));
        list.add(new CacThiTA("Thì Tương Lai Gần", "Near Future"));
        list.add(new CacThiTA("Thì Tương Lai Tiếp Diễn", "Future Continuous"));
        list.add(new CacThiTA("Thì Tương Lai Hoàn Thành", "Future Perfect"));
        list.add(new CacThiTA("Thì Tương Lai Hoàn Thành Tiếp Diễn", "Future Perfect Continuous"));

        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}