package com.example.learnenglish.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private List<String> testList;

    // Constructor để khởi tạo testList
    public TestAdapter(List<String> testList) {
        this.testList = testList;
    }

    // Phương thức để cập nhật dữ liệu
    public void setData(List<String> testList) {
        this.testList = testList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_managetest, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        String testName = testList.get(position);
        holder.bind(testName);
    }

    @Override
    public int getItemCount() {
        return testList != null ? testList.size() : 0;
    }

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
}


