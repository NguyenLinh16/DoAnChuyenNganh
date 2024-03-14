package com.example.learnenglish.Admin.managefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnenglish.R;
import com.example.learnenglish.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManagementTestFragment extends Fragment {

    private RecyclerView recyclerViewTestList;
    private TestAdapter testAdapter;
    private List<String> testList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management_test, container, false);

        // Initialize RecyclerView and TestAdapter
        recyclerViewTestList = view.findViewById(R.id.recyclerViewTestList);
        recyclerViewTestList.setLayoutManager(new LinearLayoutManager(getActivity()));
        testList = new ArrayList<>();
        testAdapter = new TestAdapter(testList); // Pass the data to the adapter

        // Set the adapter to the RecyclerView
        recyclerViewTestList.setAdapter(testAdapter);

        // Add sample data (replace this with your data)
        testList.add("Test 1");
        testList.add("Test 2");
        testList.add("Test 3");

        // Add other initialization code as needed
        Button btnAddTest = view.findViewById(R.id.btnAddTest);
        btnAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the event for adding a new test
                addTest("New Test");
            }
        });

        Button btnThoat = view.findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // Function to add a new test
    private void addTest(String testName) {
        // Add testName to the test list
        testList.add(testName);

        // Notify the adapter about the data change
        testAdapter.setData(testList);
    }
}
