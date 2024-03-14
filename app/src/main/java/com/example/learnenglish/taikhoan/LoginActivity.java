package com.example.learnenglish.taikhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Admin.AdminActivity;
import com.example.learnenglish.R;
import com.example.learnenglish.activity.MainActivity;
import com.example.learnenglish.database.DatabaseAccess;
import com.example.learnenglish.fragment.HomePageFragment;
import com.example.learnenglish.notify.MyService;
import com.example.learnenglish.singletonpatterm.MessageObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnDangnhap;
    TextView tvDangky, tvforgotPassword;
    EditText edttaikhoan, edtmatkhau;
    DatabaseAccess DB;
    FirebaseDatabase rootNode; //f_instanse
    DatabaseReference userref; //f_db
    private FirebaseAuth mAuth;
    private final MessageObject messageObject = MessageObject.getInstance();

    public static LoginActivity instance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDangnhap=(Button) findViewById(R.id.buttonDangNhap);
        tvDangky = (TextView) findViewById(R.id.textView_register);
        tvforgotPassword = (TextView) findViewById(R.id.textView_forgotPassword);
        edttaikhoan = (EditText) findViewById(R.id.editTextTaiKhoan);
        edtmatkhau = (EditText) findViewById(R.id.editTextMatKhau);

        instance = this;
        DB = DatabaseAccess.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnemail = edttaikhoan.getText().toString().trim();
                String btnpassword = edtmatkhau.getText().toString().trim();

                if(TextUtils.isEmpty(btnemail)){
                    edttaikhoan.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(btnpassword)){
                    edtmatkhau.setError("Password is Required");
                    return;
                }
                if(edtmatkhau.length() <6){
                    edtmatkhau.setError("Password Must be >=6  character");
                    return;
                }

                mAuth.signInWithEmailAndPassword(btnemail, btnpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                            DB.iduser = mAuth.getCurrentUser().getUid();
                            DB.CapNhapUser(DB.iduser);
                            DB.capnhapthongtin(DB.iduser, "","");
                            rootNode = FirebaseDatabase.getInstance();
                            userref = rootNode.getReference("User").child(DB.iduser);
                            userref.child("role").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    int roleValue = Integer.parseInt(task.getResult().getValue().toString());

                                    Intent intent;
                                    if (roleValue == 0 ){
                                        intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    }else {
                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                    }

                                    startActivity(intent);
                                }
                            });
                        }else {
                            Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void clickStopService() {
        Intent intent=new Intent(this, MyService.class);
        stopService(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}