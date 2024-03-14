package com.example.learnenglish.activity;

import android.content.ClipData;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.R;
import com.example.learnenglish.adapter.NguPhapAdapter;
import com.example.learnenglish.model.CacThiTA;
import com.example.learnenglish.model.NguPhap;

import java.util.ArrayList;
import java.util.List;

import kotlin.io.LineReader;

public class NguPhapActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNP;
    private NguPhapAdapter nguPhapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_phap);

        //Create and set up the RecyclerView
        recyclerViewNP = findViewById(R.id.recyclerViewnguphap);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewNP.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewNP.addItemDecoration(itemDecoration);


        nguPhapAdapter = new NguPhapAdapter(nguPhapList(), this);
        recyclerViewNP.setAdapter(nguPhapAdapter);
    }

    //create list of data
    private List<NguPhap> nguPhapList() {
        List<NguPhap> list = new ArrayList<>();
        list.add(new NguPhap("Câu bị động (passive voice)","..............."));
        list.add(new NguPhap("Câu điều kiện (conditional sentences)","..............."));
        list.add(new NguPhap("Mệnh đề quan hệ (Relative clauses)","..............."));
        list.add(new NguPhap("Câu giả định (subjunctive)","..............."));
        list.add(new NguPhap("Quá khứ phân từ (past participle) và hiện tại phân từ (present participle)","..............."));
        list.add(new NguPhap("Các loại câu hỏi hay xuất hiện trong tiếng Anh (the question)","..............."));
        list.add(new NguPhap("Từ loại (parts of speech)","..............."));


        return list;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
