package com.example.learnenglish.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class TestDoneActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    int numTrue=0;
    int numFalse=0;
    int numNoAns=0;
    TextView txtCaudung, txtCausai, txtChuaTraloi, txtTongdiem;
    Button btnChoilai, btnThoat;
    ImageButton btnShare;
    String sharePath = "no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        printKeyHash();
        Intent intent= getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getSerializableExtra("arr_Ques");
        anhXa();
        checkResult();
        txtCaudung.setText(""+numTrue);
        txtCausai.setText(""+numFalse);
        txtChuaTraloi.setText(""+numNoAns);
        int tongDiem=10*numTrue;
        txtTongdiem.setText(""+tongDiem);
        final int viTri= Integer.valueOf(arr_QuesBegin.get(2).getBaiTest())-1;
        btnChoilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(TestDoneActivity.this, ScreenSlidePagerActivity.class);
                intent.putExtra("viTri", viTri);
                startActivity(intent);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(TestDoneActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                takeScreenShot();
            }
        }, 1000);

        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        share(sharePath);
    }

    public void anhXa(){
        txtCaudung=(TextView) findViewById(R.id.textview_caudung);
        txtCausai=(TextView) findViewById(R.id.textview_causai);
        txtChuaTraloi=(TextView) findViewById(R.id.textview_chuaTraloi);
        txtTongdiem=(TextView) findViewById(R.id.textview_tongDiem);
        btnChoilai=(Button) findViewById(R.id.Button_Choilai);
        btnThoat=(Button)findViewById(R.id.Button_Thoat);
        btnShare=(ImageButton) findViewById(R.id.button_share);
    }
    //Phương thức kiểm tra kết quả của bài kiểm tra dựa trên câu trả lời của người dùng và câu trả lời đúng.
    public void checkResult(){
        for(int i=0;i<arr_QuesBegin.size();i++){
            if(arr_QuesBegin.get(i).getTraLoi().equals("")==true){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getTraLoi().equals(arr_QuesBegin.get(i).getAnswer())==true){
                numTrue++;
            }else numFalse++;
        }
    }
    //Phương thức này chụp màn hình và lưu ảnh chụp vào một tệp tin.
    public void takeScreenShot(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpeg";
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache(), 0, 0, v1.getWidth(), v1.getHeight() - 500);
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            String filePath = imageFile.getPath();
            sharePath = filePath;
            Log.d("sharepath",sharePath);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
//Phương thức này chia sẻ ảnh chụp màn hình sử dụng Intent.
    public void share(String sharePath) {
        File file = new File(sharePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent .setType("image/*");
        intent .putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }
//
    private void printKeyHash() {
        try {
            //Lấy Thông tin Gói Ứng dụng
            PackageInfo info = getPackageManager().getPackageInfo("com.example.learnenglish",
                    PackageManager.GET_SIGNATURES);
            //Hàm sẽ lặp qua mảng Signature trong thông tin gói ứng dụng
            for (Signature signature : info.signatures){
                //Sử dụng MessageDigest để tạo một đối tượng có thể sử dụng để tính toán giá trị băm (hash).
                // Trong trường hợp này, sử dụng thuật toán băm SHA.
                MessageDigest md = MessageDigest.getInstance("SHA");
                //Cập nhật đối tượng MessageDigest với dữ liệu của chữ ký.
                // Điều này đảm bảo rằng chữ ký được sử dụng để tạo key hash
                md.update(signature.toByteArray());
                //Tính toán giá trị băm cuối cùng (digest()) và chuyển đổi nó thành chuỗi Base64 sử dụng Base64.encodeToString().
                // Sau đó, in giá trị băm (key hash) vào Log với tag là "KeyHash"
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        }
        //Xử lý ngoại lệ nếu có bất kỳ vấn đề nào xảy ra trong quá trình lấy thông tin về gói ứng dụng hoặc tạo đối tượng MessageDigest.
        // Các thông báo lỗi sẽ được in ra Log.
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
