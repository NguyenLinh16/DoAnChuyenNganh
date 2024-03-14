package com.example.learnenglish.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnenglish.activity.ScreenSlidePagerActivity;
import com.example.learnenglish.R;

public class TestFragment extends Fragment {
    private ListView lv_test;
    private String[] list_test= {"Unit 1", "Unit 2", "Unit 3", "Unit 4","Unit 5", "Unit 6", "Unit 7", "Unit 8", "Unit 9", "Unit 10", "Unit 11", "Unit 12", "Unit 13"};

    public TestFragment() {
    }


    @Override
    // Phương thức này được gọi khi Fragment được tạo để hiển thị giao diện người dùng.
    // Nó inflate layout từ tệp fragment_test.xml, thiết lập dữ liệu cho ListView và trả về View chứa layout
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test, container, false);
        lv_test= (ListView) view.findViewById(R.id.lisstview_test);
        ArrayAdapter adapter=new ArrayAdapter(getActivity(), R.layout.custom_dongbaitest, list_test);
        lv_test.setAdapter(adapter);
        return view;
    }

    @Override
    //Phương thức này được gọi khi hoạt động đã được tạo (activity đã được tạo).
    // Nó thiết lập OnItemClickListener cho ListView để bắt sự kiện khi một mục trong danh sách được chọn.
    //Khi một mục được chọn, nó hiển thị một Toast thông báo chứa tên bài kiểm tra.
    //Sau đó, nó tạo một Intent để chuyển đến ScreenSlidePagerActivity và chuyển điểm mục được chọn dưới dạng "viTri".
    //startActivity(intent) được gọi để mở màn hình mới
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // XỬ lí sự kiện khi một item được chọn trong listView
                Toast.makeText(getActivity(), list_test[position], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ScreenSlidePagerActivity.class);
                intent.putExtra("viTri", position);
                startActivity(intent);
            }
        });
    }
}
