package com.example.learnenglish.activity;
import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;  // Corrected import
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.learnenglish.Admin.AdminActivity;
import com.example.learnenglish.database.Database;
import com.example.learnenglish.database.DatabaseAccess;
import com.example.learnenglish.fragment.AlphabetFragment;
import com.example.learnenglish.fragment.HomePageFragment;
import com.example.learnenglish.fragment.SearchFragment;
import com.example.learnenglish.fragment.TestFragment;
import com.example.learnenglish.fragment.VocabularyFragment;
import com.example.learnenglish.R;
import com.example.learnenglish.singletonpatterm.MessageObject;
import com.example.learnenglish.taikhoan.LoginActivity;
import com.example.learnenglish.taikhoan.User;
import com.google.android.material.navigation.NavigationView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private final MessageObject messageObject = MessageObject.getInstance();
    final String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    DatabaseAccess DB;
    User user;

    // Phương thức để chuyển đến Fragment
    private void loadYourFragment(Fragment fragment) {
        // Tạo một FragmentTransaction để thêm hoặc thay thế Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Thực hiện thay thế Fragment trong container
        transaction.replace(R.id.fragment_container, fragment);

        // Thêm Fragment vào Back Stack (nếu cần)
        transaction.addToBackStack(null);

        // Hoàn tất giao dịch
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = DatabaseAccess.getInstance(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.draw_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomePageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }
        LayoutUser();


    }


    private void LayoutUser() {
        database = Database.initDatabase(MainActivity.this, DATABASE_NAME);

        // Specify the columns you want to retrieve
        String[] projection = {"ID_User", "HoTen", "Point", "Email", "SDT", "Role"};

        // Use a try-with-resources statement to ensure the cursor is closed
        try (Cursor cursor = database.query("User", projection, "ID_User = ?", new String[]{String.valueOf(DB.iduser)}, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                // Access data from the cursor
                String Iduser = cursor.getString(cursor.getColumnIndexOrThrow("ID_User"));
                String HoTen = cursor.getString(cursor.getColumnIndexOrThrow("HoTen"));
                int Point = cursor.getInt(cursor.getColumnIndexOrThrow("Point"));
                String Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
                String SDT = cursor.getString(cursor.getColumnIndexOrThrow("SDT"));
                int Role = cursor.getInt(cursor.getColumnIndexOrThrow("Role"));

                user = new User(Iduser, HoTen, Point, Email, SDT, Role);
            } else {
                // Handle the case when the cursor is empty
                Log.e(TAG, "Cursor is empty");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during database access
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                loadYourFragment(new HomePageFragment());
                break;
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomePageFragment()).commit();
            case R.id.nav_admin:
            if (user.getRole()==0) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
            else {
                messageObject.ShowDialogMessage(Gravity.CENTER,
                        MainActivity.this,
                        "KHÔNG THỂ TRUY CẬP!!Tài khoản của bạn không phải Quản Lý!!",
                        0);
            }
                break;
            case R.id.nav_vocabulary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VocabularyFragment()).commit();
                break;
            case R.id.LuyenNghe:
                Intent intent = new Intent(MainActivity.this, LuyenNgheActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_learnalphabet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlphabetFragment()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit();
                break;
            case R.id.nav_test:
                TestFragment testFragment= new TestFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,testFragment,testFragment.getTag()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_NguPhapTA:
                Intent intent2 = new Intent(MainActivity.this, NguPhapActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_cacThiTA:
                Intent intent3 = new Intent(MainActivity.this, CacThiTrongTAActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
