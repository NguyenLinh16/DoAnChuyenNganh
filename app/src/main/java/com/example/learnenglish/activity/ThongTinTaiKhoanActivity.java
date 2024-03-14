package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish.R;
import com.example.learnenglish.database.DatabaseAccess;
import com.example.learnenglish.taikhoan.User;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {

    final  String DATABASE_NAME = "HocNgonNgu.db";
    DatabaseAccess DB;
    SQLiteDatabase database;
    ImageView imghome;
    EditText tvHoten,tvEmail,tvSdt,tvUID;
    TextView tvtaikhoan, tvTen,tvPoint;
    Button btnCapNhat;
    String iduser;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
    }
}