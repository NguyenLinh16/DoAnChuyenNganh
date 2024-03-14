package com.example.learnenglish.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.R;

public class TestViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewTestName;

    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTestName = itemView.findViewById(R.id.recyclerViewTestList);
    }

    public void bind(String testName) {
        textViewTestName.setText(testName);
    }
}
