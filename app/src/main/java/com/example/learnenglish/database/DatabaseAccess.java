package com.example.learnenglish.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.learnenglish.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    final String DATABASE_NAME = "HocNgonNgu.db";
    FirebaseDatabase rootNode; // firebase instanse
    DatabaseReference userref; // firebase db
    private static  DatabaseAccess instance;
    Cursor c = null;
    public String iduser;
    Map<String, String> user; // Map lưu dữ liệu dạng String: String ---> HoTen: Linh
    Map<String, Long> diem; //Map lưu trữ dữ liệu dạng String: Long
    Map<String, Long> role;
    // khai báo biến class DatabaseAccess
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //Khởi tạo Instance và mở database

    //"getInstance" phương thức để đảm bảo chỉ có một đối tượng "DatabaseAccess duy nhất được tạo ra.
    public static DatabaseAccess getInstance(Context context){
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    //Mở cơ sở dữ liệu SQLite để có thể ghi
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    //Đóng cở sở dữ liệu SQLite khi không cần thiết
    public void close(){
        if (db != null){
            this.db.close();
        }
    }
    //Insert Data vào cơ sở dữ liệu
    //Phương thức để chèn dữ liệu người dùng mới vào cơ sở dữ liệu SQLite
    public Boolean insertData(String iduser,String hoten, String email,String sdt,int diem, int role)
    {
        //sử dụng phương thức getWritableDatabase() của đối tượng openHelper để lấy kết nối cơ sở dữ liệu có thể ghi.
        db = openHelper.getWritableDatabase();
        //tạo một đối tượng ContentValues mới. Đối tượng này sẽ được sử dụng để lưu trữ dữ liệu của người dùng trước khi chèn vào cơ sở dữ liệu
        ContentValues contentValues = new ContentValues();
        //sử dụng phương thức put() của đối tượng ContentValues để thêm khóa ID_User và giá trị iduser vào đối tượng
        contentValues.put("ID_User",iduser);
        contentValues.put("HoTen",hoten);
        contentValues.put("Point",diem);
        contentValues.put("Email",email);
        contentValues.put("SDT",sdt);
        contentValues.put("Role", role);
        //sử dụng phương thức insert() của đối tượng SQLiteDatabase để chèn dữ liệu được lưu trữ trong đối tượng ContentValues vào bảng User của cơ sở dữ liệu
        long result = db.insert("User",null,contentValues);
        //kiểm tra giá trị trả về của phương thức insert().
        // Nếu giá trị trả về là -1, thì chèn dữ liệu không thành công.
        // Trong trường hợp này, hàm trả về false.
        //Ngược lại, nếu chèn dữ liệu thành công, hàm trả về true
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }

    }
    //Phương thức để kiểm tra xem một tài khoản có tồn tại trong cở sở dữ liệu SQLite hay không
    public Boolean checktaikhoan (String email) {
        //sử dụng phương thức getWritableDatabase() của đối tượng openHelper để lấy kết nối cơ sở dữ liệu có thể ghi
        db = openHelper.getWritableDatabase();

        //sử dụng phương thức rawQuery() của đối tượng SQLiteDatabase để thực thi truy vấn cơ sở dữ liệu
        //Select * from User where Email = ? là truy vấn được thực thi. Truy vấn này chọn tất cả các cột (*) từ bảng User nơi cột Email khớp với giá trị email được cung cấp.
        //? là dấu phẩy than được sử dụng để buộc giá trị email an toàn, ngăn chặn các lỗ hổng SQL injection.
        //new String[]{email} cung cấp giá trị được buộc vào dấu phẩy than
        Cursor cursor = db.rawQuery("Select * from User where Email = ?", new String[]{email});

        //kiểm tra số hàng được trả về bởi truy vấn.
        // Nếu lớn hơn 0, thì có ít nhất một người dùng với email đã cho tồn tại
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    //kiểm tra xem dữ liệu người dùng đã có trong SQLite chưa và cập nhập từ Firebase xuống nếu cần.
    public void CapNhapUser (String iduser) {
        db = openHelper.getWritableDatabase();
        rootNode = FirebaseDatabase.getInstance();
        userref = rootNode.getReference("User").child(iduser);

        Cursor cursor = db.rawQuery("Select * from User where ID_User = ?", new String[]{iduser});
        if (cursor.getCount()>0){
            //Th1 đã có dữ liệu ở sqlite
            cursor.moveToFirst();
            userref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    user = (Map<String, String>) datasnapshot.getValue();
                    diem = (Map<String, Long>) datasnapshot.getValue();
                    role = (Map<String, Long>) datasnapshot.getValue();

                    //db = openHelper.getWritableDatabase();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Hoten", user.get("hoten"));

                    Long pointValue = diem.get("point");
                    int Point = (pointValue != null) ? pointValue.intValue() : 0;
                    contentValues.put("point", Point);
                    contentValues.put("SDT", user.get("sdt"));

                    //đảm bảo giá trị role không phải là 0 trước khi truy cập nó
                    if (role != null) {
                        int Role = role.get("role").intValue();
                        contentValues.put("Role", Role);
                    }
                    db.update("User", contentValues, "ID_User = ?", new String[]{iduser});
                    db.close(); // Đóng kết nối sau khi cập nhật
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            // Th2: chưa có dữ liệu ở sqlite
            rootNode = FirebaseDatabase.getInstance();
            userref = rootNode.getReference("User").child(iduser);

            userref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user = (Map<String, String>) dataSnapshot.getValue();
                    diem = (Map<String, Long>) dataSnapshot.getValue();
                    role = (Map<String, Long>) dataSnapshot.getValue();

                    db = openHelper.getWritableDatabase();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ID_User", iduser);
                    contentValues.put("HoTen", user.get("hoten"));

                    Long pointValue = diem.get("point");
                    //Thay thế 0 bằng giá trị mặc định nếu cần.
                    int Point = (pointValue != null) ? pointValue.intValue() : 0;
                    contentValues.put("Point", Point);
                    contentValues.put("Email", user.get("email"));
                    contentValues.put("SDT", user.get("sdt"));

                    //Đảm bảo giá trị Role không phải là null trước khi truy cậpnos
                    if (role != null){
                        int Role = role.get("role").intValue();
                        contentValues.put("Role", Role);
                    }

                    db.insert("User", null, contentValues);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    db.close();
                }
            });
        }
    }
    //Cập nhập thông tin ngươi dùng trong cả Sqlite và firebase
    public Boolean capnhapthongtin(String iduser, String hoten, String sdt) {

        rootNode = FirebaseDatabase.getInstance();
        userref = rootNode.getReference("User").child(iduser);

        userref.child("hoten").setValue(hoten);
        userref.child("sdt").setValue(sdt);

        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("hoten", hoten);
        contentValues.put("SDT",sdt);
        Cursor cursor = db.rawQuery("Select * from User where ID_User = ?", new String[]{iduser});
        if(cursor.getCount()>0) {
            long result = db.update("User",contentValues,"ID_User = ?", new String[]{iduser});
            if(result==-1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return  false;
        }
    }
    //Cập nhập điểm người dùng trong cả Sqlite và Firebase
    public Boolean capnhatdiem(String iduser,int Point, int PointPlus){

        //Cập Nhật User lên FireBase
        rootNode = FirebaseDatabase.getInstance();
        userref = rootNode.getReference("User").child(iduser);
        userref.child("point").setValue(Point+PointPlus);

        //Cập nhật dữ liệu lên SQLite
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Point", Point + PointPlus);
        Cursor cursor = db.rawQuery("Select * from User where ID_User = ?", new String[]{iduser});
        if(cursor.getCount() >0) {
            long result = db.update("User",contentValues,"ID_User = ?", new String[]{iduser});
            if(result==-1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return  false;
        }
    }
}
