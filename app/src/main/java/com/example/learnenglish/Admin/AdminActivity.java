package com.example.learnenglish.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnenglish.Admin.managefragment.ManageVocabularyFragment;
import com.example.learnenglish.Admin.managefragment.ManagementTestFragment;
import com.example.learnenglish.R;
import com.example.learnenglish.activity.TestDoneActivity;
import com.example.learnenglish.activity.ThongTinTaiKhoanActivity;
import com.example.learnenglish.database.Database;
import com.example.learnenglish.database.DatabaseAccess;
import com.example.learnenglish.fragment.HomePageFragment;
import com.example.learnenglish.singletonpatterm.MessageObject;
import com.example.learnenglish.taikhoan.LoginActivity;
import com.example.learnenglish.taikhoan.User;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private MessageObject messageObject = MessageObject.getInstance();
    ArrayList<String> adminList;

    private static final String MANAGE_VOCABULARY_FRAGMENT_TAG = "ManageVocabularyFragment";
    private static final String MANAGEMENT_TEST_FRAGMENT_TAG = "ManagementTestFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnManageVocabulary = findViewById(R.id.btnManageVocabulary);
        btnManageVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến chức năng quản lý từ vựng
                loadFragment(new ManageVocabularyFragment(), MANAGE_VOCABULARY_FRAGMENT_TAG);
            }
        });

        Button btnManageTest = findViewById(R.id.btnManageTest);
        btnManageTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ManagementTestFragment(), MANAGEMENT_TEST_FRAGMENT_TAG);
            }
        });
    }

    private void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}